package Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.tecdehcode.week1_0706012010050.R;

public class User implements Parcelable {

    private String nama, alamat, umur;

    public User(String nama, String alamat, String umur) {
        this.nama = nama;
        this.alamat = alamat;
        this.umur = umur;
    }
    public User() {
        this.nama = "";
        this.alamat = "";
        this.umur = "";
    }


    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getUmur() {
        return umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

    protected User(Parcel in) {
        nama = in.readString();
        alamat = in.readString();
        umur = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nama);
        dest.writeString(alamat);
        dest.writeString(umur);
    }

  


}

