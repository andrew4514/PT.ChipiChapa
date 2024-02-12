package lntMid;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
	// PT ChipiChapa
	
	Scanner scan = new Scanner(System.in);
	ArrayList<Data> data_karyawan = new ArrayList<>();
	
	public void menu() {
		System.out.println("PT ChipiChapa");
		System.out.println("1. Insert");
		System.out.println("2. View");
		System.out.println("3. Update");
		System.out.println("4. Delete");
		System.out.println("5. Exit");
		System.out.print(">> ");
	}
	
	public String random_kode( ) {
		Random random = new Random();
		char char1 = (char) (random.nextInt(26) + 'a');
		char char2 = (char) (random.nextInt(26) + 'a');
		
		int randomNumber = random.nextInt(10000);
		
		return String.format("%C%C-%04d", char1, char2, randomNumber);
	}
	
	public boolean uniqueId(String kode_karyawan) {
		boolean output = false;
		for(int i = 0; i < data_karyawan.size(); i++) {
			if( data_karyawan.get(i).getKode_karyawan().equals(kode_karyawan) ) output = true;
		}
		return output;
	}
	
	
	
	// Insert
	public void insert() {
		String nama, jenis_kelamin, jabatan, kode_karyawan;
		int gaji = 0;
		boolean condition = false;
		
		do {
			System.out.print("Input nama Karyawan [>= 3]: ");
			nama = scan.nextLine();
			
			for(int i = 0; i < nama.length(); i++) {
				if(Character.isLetter(nama.charAt(i)) || nama.charAt(i) == ' ') {
					condition = false;
				} else {
					condition = true; 
					break;
				}
			}
			
		} while(nama.trim().length() < 3 || condition);
		
		
		do {
			System.out.print("Input jenis kelamin [Laki-laki | Perempuan] (Case Sensitive): ");
			jenis_kelamin = scan.nextLine();
		} while ( !(jenis_kelamin.equals("Laki-laki") || jenis_kelamin.equals("Perempuan")) );
		
		
		do {
			System.out.print("Input jabatan [Manager | Supervisor | Admin] (Case Sensitive): ");
			jabatan = scan.nextLine();
			
			if(jabatan.equals("Manager")) gaji = 8000000;
			else if(jabatan.equals("Supervisor")) gaji = 6000000;
			else gaji = 4000000;
			
		} while ( !(jabatan.equals("Manager") || jabatan.equals("Supervisor") || jabatan.equals("Admin")) );
		
		do {
			kode_karyawan = random_kode();
		} while( uniqueId(kode_karyawan));
		
		Data insert = new Data(nama, jenis_kelamin, jabatan, kode_karyawan, gaji);
		data_karyawan.add(insert);
		
		System.out.println("Berhasil menambahkan karyawan dengan id " + kode_karyawan);
		
		// cek total karyawan dengan jabatan yang sama
		int total = 0;
		for (int i = 0; i < data_karyawan.size(); i++) {
			boolean cek = data_karyawan.get(i).getJabatan().equals(jabatan);
			if(cek) {
				total++;
			}
		}
		
		// menentukan bonus karyawan
		if(total > 3) {
			Double nilaiBonus = 0.0;
			ArrayList<String> idKaryawan = new ArrayList<>();
			for (int i = 0; i < data_karyawan.size(); i++) {
				Data cek = data_karyawan.get(i);
				if(cek.getJabatan().equals(jabatan) && i != data_karyawan.size() - 1) {
					if(jabatan.equals("Manager")) nilaiBonus = 10d;
					else if(jabatan.equals("Supervisor")) nilaiBonus = 7.5;
					else nilaiBonus = 5d;
					
					int bonus = (int) (cek.getGaji() * (nilaiBonus / 100) + cek.getGaji());
					
					cek.setGaji(bonus);
					idKaryawan.add(cek.getKode_karyawan());
				}
			}
			
			System.out.print("Bonus sebesar " + nilaiBonus + "% telah diberikan kepada karyawan dengan id ");
			for (int i = 0; i < idKaryawan.size(); i++) {
				if (i != idKaryawan.size() - 1) System.out.print(idKaryawan.get(i) + ", ");
				else System.out.print(idKaryawan.get(i) + "\n");
			}
			
		}
		
		System.out.println("Enter to return");
		scan.nextLine();
	}
	
	
	// view
	public void view() {
		if(data_karyawan.size() > 0) {
			
			// Pengurutan data berdasarkan nama karyawan
			for (int j = 0; j < data_karyawan.size(); j++) {
				for (int k = 0; k < data_karyawan.size() - j - 1; k++) {
					Data data1 = data_karyawan.get(k);
					Data data2 = data_karyawan.get(k+1);
					
					if( data1.getNama().compareToIgnoreCase(data2.getNama()) > 0 ) {
						data_karyawan.set(k, data2);
						data_karyawan.set(k+1, data1);
					}
				}
			}
			
			
			int i = 1;
			System.out.println("|---------------------------------------------------------------------------------------------|");
			System.out.printf("|%-2s  |%-16s |%-24s |%-14s |%-13s |%-13s| \n", "No","Kode Karyawan", "Nama Karyawan", "Jenis Kelamin", "Jabatan", "Gaji Karyawan");
			System.out.println("|----|-----------------|-------------------------|---------------|--------------|-------------|");
			for (Data data : data_karyawan) {
				System.out.printf("|  %2s| %16s| %24s| %14s| %13s| %12s| \n", i, data.getKode_karyawan(), data.getNama(), data.getJenis_kelamin(), data.getJabatan(), data.getGaji());
				i++;
			}						
			System.out.println("|---------------------------------------------------------------------------------------------|");
		} else {
			System.out.println("Tidak ada data yang ditampilkan\n");
		}
	}
	
	
	
	public void update() {
		int index = 0;
		do {
			try {
				System.out.print("Input nomor urutan karyawan yang ingin diupdate: ");
				index = scan.nextInt(); scan.nextLine();
			} catch (Exception e) {
				System.out.println("Input harus angka!");
				scan.nextLine();
			}
		} while ( !(index > 0 && index <= data_karyawan.size()) );
		
		
		Data data = data_karyawan.get(index - 1);
		String nama, jenis_kelamin, jabatan;
		int gaji = 0;
		boolean condition = true;
		
		do {
			System.out.print("Input nama Karyawan [>= 3]: ");
			nama = scan.nextLine();
			
			for(int i = 0; i < nama.length(); i++) {
				if(Character.isLetter(nama.charAt(i)) || nama.charAt(i) == ' ') {
					condition = true;
				} else {
					condition = false; 
					break;
				}
			}            
		
		} while( !(nama.trim().length() >= 3 && condition) && !(nama.equals("0")) );
		
		
		do {
			System.out.print("Input jenis kelamin [Laki-laki | Perempuan] (Case Sensitive): ");
			jenis_kelamin = scan.nextLine();
		} while ( !(jenis_kelamin.equals("Laki-laki") || jenis_kelamin.equals("Perempuan")) && !(jenis_kelamin.equals("0")) );
		
		
		do {
			System.out.print("Input jabatan [Manager | Supervisor | Admin] (Case Sensitive): ");
			jabatan = scan.nextLine();
			
			if(jabatan.equals("Manager")) gaji = 8000000;
			else if(jabatan.equals("Supervisor")) gaji = 6000000;
			else gaji = 4000000;
			
		} while ( !(jabatan.equals("Manager") || jabatan.equals("Supervisor") || jabatan.equals("Admin")) && !(jabatan.equals("0")) );			
		
		
		if( nama.equals("0") ) nama = data.getNama();
		if( jenis_kelamin.equals("0") ) jenis_kelamin = data.getJenis_kelamin();
		String cekJabatan = jabatan;
		if( jabatan.equals("0") ) {
			jabatan = data.getJabatan(); 
			gaji = data.getGaji();
		}
		
		Data update = new Data(nama, jenis_kelamin, jabatan, data.getKode_karyawan(), gaji);
		data_karyawan.set(index - 1, update);
		
		System.out.println("Berhasil mengubah karyawan dengan id " + data.getKode_karyawan());
		
		if(!cekJabatan.equals("0")) {
			// cek total karyawan dengan jabatan yang sama
			int total = 0;
			for (int i = 0; i < data_karyawan.size(); i++) {
				boolean cek = data_karyawan.get(i).getJabatan().equals(jabatan);
				if(cek) {
					total++;
				}
			}
			
			// menentukan bonus karyawan
			if(total > 3) {
				Double nilaiBonus = 0.0;
				ArrayList<String> idKaryawan = new ArrayList<>();
				for (int i = 0; i < data_karyawan.size(); i++) {
					Data cek = data_karyawan.get(i);
					if(cek.getJabatan().equals(jabatan) && i != data_karyawan.size() - 1) {
						if(jabatan.equals("Manager")) nilaiBonus = 10d;
						else if(jabatan.equals("Supervisor")) nilaiBonus = 7.5;
						else nilaiBonus = 5d;
						
						int bonus = (int) (cek.getGaji() * (nilaiBonus / 100) + cek.getGaji());
						
						cek.setGaji(bonus);
						idKaryawan.add(cek.getKode_karyawan());
					}
				}
				
				System.out.print("Bonus sebesar " + nilaiBonus + "% telah diberikan kepada karyawan dengan id ");
				for (int i = 0; i < idKaryawan.size(); i++) {
					if (i != idKaryawan.size() - 1) System.out.print(idKaryawan.get(i) + ", ");
					else System.out.print(idKaryawan.get(i) + "\n");
				}
		}
	}
		
		System.out.println("Enter to return");
		scan.nextLine();
	}
	
	
	
	public void delete() {
		int index = 0;
		do {
			try {
				System.out.print("Input nomor urutan karyawan yang ingin dihapus: ");
				index = scan.nextInt(); scan.nextLine();
			} catch (Exception e) {
				System.out.println("Input harus angka!");
				scan.nextLine();
			}
		} while ( !(index > 0 && index <= data_karyawan.size()) );
		
		String kode = data_karyawan.get(index - 1).getKode_karyawan();
		data_karyawan.remove(index - 1);
		
		System.out.println("Karyawan dengan kode " + kode + " berhasil dihapus");
		System.out.println("Enter to return");
		scan.nextLine();
	}
	
	
	// Main
	public Main() {
		int inputMenu = 0;
		
		do {
			
			menu();
			try {
				inputMenu = scan.nextInt(); scan.nextLine();
				if(inputMenu <= 0 || inputMenu > 5) System.out.println("Pilih Menu 1 - 5!\n");
			} catch (Exception e) {
				System.out.println("Input harus angka!\n");
				scan.next();
				inputMenu = 0;
			}
			
			switch (inputMenu) {
				case 1:
					insert();
				break;
				
				case 2:
					view();
					System.out.println("Enter to return");
					scan.nextLine();
				break;
				
				case 3:
					view();
					if(data_karyawan.size() != 0) {
						update();						
					}
				break;
				
				case 4: 
					view();
					if(data_karyawan.size() != 0) {
						delete();						
					}
				break;
			}
			
		}while(inputMenu != 5);
		
		
	}
	
	public static void main(String[] args) {
		new Main();
	}

}
