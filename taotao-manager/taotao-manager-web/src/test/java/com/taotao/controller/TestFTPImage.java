package com.taotao.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

public class TestFTPImage {
	
	/**
	 * ftp服务上传图片测试成功
	 */
	@Test
	public void testFtp1() {
		// 创建客户端对象
		FTPClient ftp = new FTPClient();
		InputStream local = null;
		try {
			// 连接ftp服务器
			ftp.connect("127.0.0.1", 21);
			// 登录
			boolean login = ftp.login("cwp", "cwp");
			// 设置上传路径
			String path = "image";
			// 检查上传路径是否存在 如果不存在返回false
			boolean flag = ftp.changeWorkingDirectory(path);
			if (!flag) {
				// 创建上传的路径 该方法只能创建一级目录，在这里如果/home/ftpuser存在则可创建image
				boolean dir = ftp.makeDirectory(path);
			}
			// 指定上传路径
			boolean cha = ftp.changeWorkingDirectory(path);
			// 指定上传文件的类型 二进制文件
			boolean set = ftp.setFileType(FTP.BINARY_FILE_TYPE);
			// 读取本地文件
			File file = new File("C:\\Users\\叔公\\Desktop\\常用文件\\。\\0.jpg");
			local = new FileInputStream(file);
			// 第一个参数是文件名
			String fileName = file.getName();
			boolean store = ftp.storeFile("hello.jpg", local);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭文件流
				local.close();
				// 退出
				ftp.logout();
				// 断开连接
				ftp.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
