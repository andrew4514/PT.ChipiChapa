package lntMid;

public class Data {
	private String nama, jenis_kelamin, jabatan, kode_karyawan;
	private int gaji;

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getJenis_kelamin() {
		return jenis_kelamin;
	}

	public void setJenis_kelamin(String jenis_kelamin) {
		this.jenis_kelamin = jenis_kelamin;
	}

	public String getJabatan() {
		return jabatan;
	}

	public void setJabatan(String jabatan) {
		this.jabatan = jabatan;
	}

	public String getKode_karyawan() {
		return kode_karyawan;
	}

	public void setKode_karyawan(String kode_karyawan) {
		this.kode_karyawan = kode_karyawan;
	}
	
	public int getGaji() {
		return gaji;
	}

	public void setGaji(int gaji) {
		this.gaji = gaji;
	}

	public Data(String nama, String jenis_kelamin, String jabatan, String kode_karyawan, int gaji) {
		super();
		this.nama = nama;
		this.jenis_kelamin = jenis_kelamin;
		this.jabatan = jabatan;
		this.kode_karyawan = kode_karyawan;
		this.gaji = gaji;
	}
}
