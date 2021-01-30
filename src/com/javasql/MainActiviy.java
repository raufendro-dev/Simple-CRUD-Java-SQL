package com.javasql;

import java.sql.*;
import java.util.Scanner;

public class MainActiviy {
	
	public static void main(String[] args) throws SQLException {
		int pilihawal=0;
		Scanner pilih = new Scanner(System.in);
		System.out.println("Pilih Menu :");
		System.out.println("1. Login");
		System.out.println("2. Daftar");
		System.out.print("1/2?");
		pilihawal=pilih.nextInt();
		if(pilihawal==1) {
			login();
		} else if(pilihawal==2) {
			daftar();
		
		} else {
			System.out.println("Input yang anda masukan salah");
		}
		
		
			
		
	}
	public static void login() throws SQLException{
		Connection koneksi = SQLConnector.dbconnector();
		int pilihakun=0;
		String user, usershow, pass, full, halo;
		Scanner input = new Scanner(System.in);
		System.out.print("Username: ");
		user=input.nextLine();
		System.out.print("Password: ");
		pass=input.nextLine();
		System.out.println("");
		String query = "SELECT * FROM users where username=? and password=?;";
		PreparedStatement pst = koneksi.prepareStatement(query);
		pst.setString(1, user);
		pst.setString(2, pass);
		ResultSet rs=pst.executeQuery();
		int hit=0;
		while(rs.next()) {
			halo = rs.getString("fullname");
			System.out.println("Halo, "+halo);
			hit=hit+1;
			
			
		}
		if(hit==1) {
			
			
			System.out.println("");
			System.out.println("Pilih Menu :");
			System.out.println("1. Lihat data saya");
			System.out.println("2. Ganti data saya");
			System.out.println("3. Hapus akun saya");
			System.out.print("1/2?");
			System.out.println("");
			pilihakun=input.nextInt();
			if(pilihakun==1) {
				
				String lihatquery = "SELECT * FROM users where username=?;";
				PreparedStatement pst2 = koneksi.prepareStatement(lihatquery);
				pst2.setString(1, user);
				ResultSet rs2=pst2.executeQuery();
				while(rs2.next()) {
					usershow=rs2.getString("username");
					pass = rs2.getString("password");
					full = rs2.getString("fullname");

					System.out.println("Username Anda "+user);
					System.out.println("Password Anda "+pass);
					System.out.println("Nama Lengkap Anda "+full);
				}
				rs2.close();
				pst2.close();
			} else if(pilihakun==2) {
				int data;
				
				
				System.out.println("Pilih yang mau diganti :");
				System.out.println("1. Username");
				System.out.println("2. Password");
				System.out.println("3. Nama Lengkap");
				System.out.print("1/2/3?");
				data=input.nextInt();
				if(data==1) {
					System.out.print("Masukan Username Baru: ");
					String userbaru = input.next();
					String updatequeryusername = "Update users set username=? where username=?;";
					PreparedStatement pst3 = koneksi.prepareStatement(updatequeryusername);
					pst3.setString(1, userbaru);
					pst3.setString(2, user);
					pst3.execute();
					System.out.print("Data update");
					pst3.close();
					
				} else if(data==2) {
					System.out.print("Masukan Password Baru: ");
					String passbaru = input.next();
					String updatequerypassword = "Update users set password=? where username=?;";
					PreparedStatement pst3 = koneksi.prepareStatement(updatequerypassword);
					pst3.setString(1, passbaru);
					pst3.setString(2, user);
					pst3.execute();
					System.out.print("Data update");
					pst3.close();
				} else if(data==3) {
					System.out.print("Masukan Nama Lengkap Baru: ");
					String fullbaru = input.next();
					String updatequeryfullname = "Update users set password=? where username=?;";
					PreparedStatement pst3 = koneksi.prepareStatement(updatequeryfullname);
					pst3.setString(1, fullbaru);
					pst3.setString(2, user);
					pst3.execute();
					System.out.print("Data update");
					pst3.close();
				} else {
					System.out.print("Input yang anda masukan salah");
				}
				
				
				
				
				
			} else if(pilihakun==3) {
				String hapusquery = "DELETE FROM users where username=?;";
				PreparedStatement pst2 = koneksi.prepareStatement(hapusquery);
				pst2.setString(1, user);
				pst2.execute();
				System.out.print("Akun telah dihapus");
				pst2.close();
				
			}
			
			
		} else {
			System.out.println("Gagal Login");
		}
		
		rs.close();
		pst.close();
		
	}
	
	public static void daftar() throws SQLException{
		Connection koneksi = SQLConnector.dbconnector();
		String user, pass, full;
		Scanner input = new Scanner(System.in);
		System.out.print("Masukan Username Anda: ");
		user=input.nextLine();
		System.out.print("Masukan Password Anda: ");
		pass=input.nextLine();
		System.out.print("Masukan Nama Lengkap Anda: ");
		full=input.nextLine();
		System.out.println("");
		String query = "insert into users (username, password, fullname) values (?, ?, ?);";
		PreparedStatement pst = koneksi.prepareStatement(query);
		pst.setString(1, user);
		pst.setString(2, pass);
		pst.setString(3, full);
		pst.execute();
		System.out.println("Berhasil Mendaftar");
		
		pst.close();
		
	}
	public static void lihat() throws SQLException{
		Connection koneksi = SQLConnector.dbconnector();
		String id;
		String user, usershow, pass, full;
		Scanner input = new Scanner(System.in);
		System.out.print("Masukan Username Anda: ");
		user=input.nextLine();
		String query = "SELECT * FROM users where username=?;";
		PreparedStatement pst = koneksi.prepareStatement(query);
		pst.setString(1, user);
		ResultSet rs=pst.executeQuery();
		while(rs.next()) {
			id = rs.getString("id");
			usershow=rs.getString("username");
			pass = rs.getString("password");
			full = rs.getString("fullname");
			System.out.println(id);
			System.out.println(user);
			System.out.println(pass);
			System.out.println(full);
		}
		rs.close();
		pst.close();
	}
	
	

}




