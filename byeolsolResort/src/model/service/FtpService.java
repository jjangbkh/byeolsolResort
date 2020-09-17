package model.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.ibatis.javassist.ClassClassPath;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("testService")
public class FtpService {

	// 연결할 ftp ip, port, id, password
	private final String server = "files.000webhost.com";
	private final int port = 21;
	private final String user = "gyonewproject";
	private final String pw = "password01!";

	// path와 Time을 받아 ftp 에있는 이미지 삭제
	public boolean ftpdeleteEvent(String path, String Time) {
		FTPClient ftp = null;
		try {
			// ftpClient생성
			ftp = new FTPClient();
			// encoding utf-8로 설정
			ftp.setControlEncoding("utf-8");
			// 연결할 ftp 서버와 포트 입력
			ftp.connect(server, port);
			// ftp 로그인
			ftp.login(user, pw);
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			// 만약 ftp 서버에 연결하지 못했담ㄴ 연결을 끊고 실패 출력
			if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
				ftp.disconnect();
				System.out.println("연결 실패");
			} else {
				System.out.println("연결 성공");
			}
			// ftp 서버 작업중인 directory 파라미터 받은 값으로 변경
			ftp.changeWorkingDirectory("/public_html/byeolsolResort/event/" + Time);
			System.out.println(Time);
			// 삭제할 파일 path
			String deletePath = "/public_html/byeolsolResort/event/" + Time
					+ path.substring(path.lastIndexOf('/'), path.length());
			System.out.println(deletePath);
			// 제대로 삭제되면 true 반환
			if (ftp.deleteFile(deletePath)) {
				return true;
			} else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally { // 연결끊기
			if (ftp != null && ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	// 위와 같지만 board 폴더
	public void ftpdelete(String path, String Time) {

		FTPClient ftp = null;
		try {
			ftp = new FTPClient();
			ftp.setControlEncoding("utf-8");
			ftp.connect(server, port);
			ftp.login(user, pw);
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
				ftp.disconnect();
				System.out.println("연결 실패");
			} else {
				System.out.println("연결 성공");
			}
			ftp.changeWorkingDirectory("/public_html/byeolsolResort/board/" + Time);
			String deletePath = "/public_html/byeolsolResort/board/" + Time
					+ path.substring(path.lastIndexOf('/'), path.length());
			System.out.println(deletePath);
			System.out.println(ftp.deleteFile(deletePath));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ftp != null && ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	// 파일과 addTime을 갖고 ftp 이미지 올리기
	public void ftpEventImg(MultipartFile uploadFile, String addTime) {
		// 받는 변수는 request를 보낸 것에 맞게 받으시면 됩니다.
		// 웹에서 받은 MultipartFile을 File로 변환시켜줍니다.
		FTPClient ftp = null;
		// 자바 파일이 있는 폴더
		File tempDirectory = new File(System.getProperty("java.io.tmpdir"));
		System.out.println(tempDirectory.getAbsolutePath());
		try {
			// 임시 파일 생성
			File file = new File(tempDirectory.getAbsolutePath() + "/" + uploadFile.getOriginalFilename());
			if (file.createNewFile()) {
				System.out.println("생성");
			}
			// 임시파일에 upLoad파일의 byte들을 쓰고 close
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(uploadFile.getBytes());
			fos.close();

			// FTPClient를 생성합니다.
			ftp = new FTPClient();
			// 원하시는 인코딩 타입
			ftp.setControlEncoding("utf-8");
			ftp.connect(server, port);
			ftp.login(user, pw);
			// 원하시는 파일 타입
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			// 제대로 연결이 안댔을 경우 ftp접속을 끊습니다.
			if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
				ftp.disconnect();
				System.out.println("연결 실패");
			} else {
				System.out.println("연결 성공");
			}
			// 파일을 넣을 디렉토리를 설정해줍니다.
			// 파일을 넣을 폴더 생성
			ftp.mkd("/public_html/byeolsolResort/event");
			ftp.mkd("/public_html/byeolsolResort/event/" + addTime);
			System.out.println("성공?");
			// makeDirectory는 directory 생성이 필요할 때만 해주시면 됩니다.
			ftp.changeWorkingDirectory("/public_html/byeolsolResort/event/" + addTime);
			// 그 후 이전에 File로 변환한 업로드파일을 읽어 FTP로 전송합니다.
			FileInputStream fis = new FileInputStream(file);
			boolean isSucess = ftp.storeFile(uploadFile.getOriginalFilename(), fis);
			if (isSucess) {
				System.out.println("성공");
			} else {
				System.out.println("실패");
			}
			// fis ,fos를 끄고
			fis.close();
			fos.close();
			// file에서 나가고 file삭제
			System.out.println(file.exists());
			System.out.println(file.delete());
			// storeFile Method는 파일 송신결과를 boolean값으로 리턴합니다
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ftp != null && ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 위와 같음
	public String ftpImg(MultipartFile uploadFile, String addTime, String count) {
		// 받는 변수는 request를 보낸 것에 맞게 받으시면 됩니다.
		// 웹에서 받은 MultipartFile을 File로 변환시켜줍니다.
		FTPClient ftp = null;
		File tempDirectory = new File(System.getProperty("java.io.tmpdir"));
		System.out.println(tempDirectory.getAbsolutePath());
		try {
			File file = new File(tempDirectory.getAbsolutePath() + "/" + uploadFile.getOriginalFilename());
			if (file.createNewFile()) {
				System.out.println("생성");
			}
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(uploadFile.getBytes());
			fos.close();
			// FTPClient를 생성합니다.
			ftp = new FTPClient();
			// 원하시는 인코딩 타입
			ftp.setControlEncoding("utf-8");
			ftp.connect(server, port);
			ftp.login(user, pw);
			// 원하시는 파일 타입
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			// 제대로 연결이 안댔을 경우 ftp접속을 끊습니다.
			if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
				ftp.disconnect();
				System.out.println("연결 실패");
			} else {
				System.out.println("연결 성공");
			}
			// 파일을 넣을 디렉토리를 설정해줍니다.
			ftp.mkd("/public_html/byeolsolResort/board");
			ftp.mkd("/public_html/byeolsolResort/board/" + addTime);
			System.out.println("성공?");
			// makeDirectory는 directory 생성이 필요할 때만 해주시면 됩니다.
			ftp.changeWorkingDirectory("/public_html/byeolsolResort/board/" + addTime);
			// 그 후 이전에 File로 변환한 업로드파일을 읽어 FTP로 전송합니다.
			FileInputStream fis = new FileInputStream(file);
			boolean isSucess = ftp.storeFile(count + uploadFile.getOriginalFilename(), fis);
			if (isSucess) {
				System.out.println("성공");
			} else {
				System.out.println("실패");
			}
			fis.close();
			fos.close();
			System.out.println(file.exists());
			System.out.println(file.delete());
			// storeFile Method는 파일 송신결과를 boolean값으로 리턴합니다
			return "성공";
		} catch (Exception e) {
			e.printStackTrace();
			return "실패";
		} finally {
			if (ftp != null && ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 파라미터로 받은 것의 폴더의 파일 이름 List에 넣고 반환
	public List<String> ftpImgPath(String what,String value) {
		FTPClient ftp = null;
		List<String> imgPath = new ArrayList<String>();
		try {
			ftp = new FTPClient();
			// 원하시는 인코딩 타입
			ftp.setControlEncoding("utf-8");
			ftp.connect(server, port);
			ftp.login(user, pw);
			// 원하시는 파일 타입
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			// 제대로 연결이 안댔을 경우 ftp접속을 끊습니다.
			if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
				ftp.disconnect();
				System.out.println("연결 실패");
			} else {
				System.out.println("연결 성공");
			}
			ftp.changeWorkingDirectory("/public_html/byeolsolResort/" + what);
			FTPFile[] ftpFiles = ftp.listFiles();
			for (FTPFile ftpFile : ftpFiles) {
				if (ftpFile.getType() == 0) {
					if (!ftpFile.getName().equals("public_html") && !ftpFile.getName().equals("tmp")) {
						if (!ftpFile.getName().equals(".") && !ftpFile.getName().equals("..")) {
							if(ftpFile.getName().contains(value)) {
							imgPath.add("https://gyonewproject.000webhostapp.com/byeolsolResort/" + what + "/"
									+ ftpFile.getName());
							}
						}
					}
				}
			}
			boolean check = false;
			for (String strs : imgPath) {
				String s = strs.substring(strs.lastIndexOf('/')+1,strs.lastIndexOf('.'));
				if(s.equals(value)) { check = true; break;}
			}
			if(!check) {
				imgPath = new ArrayList<String>();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		return imgPath;
	}

	public boolean fileTypeCheck(MultipartFile uploadFile) {
		String fileName = uploadFile.getOriginalFilename();
		String fileType = fileName.substring(fileName.lastIndexOf('.') + 1, fileName.length()).toLowerCase();
		// 업로드 파일의 이름의 .의 index+1부터 끝까지 자르고 소문자로 변경 후 이미지 파일인지 확인
		if (fileType.equals("png") || fileType.equals("jpg") || fileType.equals("jpeg")) {
			return true;
		} else
			return false;

	}

	// 썸네일 올리기
	public boolean ftpEventThumbImg(MultipartFile uploadFile, int eventId) {
		FTPClient ftp = null;
		File tempDirectory = new File(System.getProperty("java.io.tmpdir"));
		System.out.println(tempDirectory.getAbsolutePath());
		try {
			File file = new File(tempDirectory.getAbsolutePath() + "/" + uploadFile.getOriginalFilename());
			if (file.createNewFile()) {
				System.out.println("생성");
			}
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(uploadFile.getBytes());
			fos.close();
			// FTPClient를 생성합니다.
			ftp = new FTPClient();
			// 원하시는 인코딩 타입
			ftp.setControlEncoding("utf-8");
			ftp.connect(server, port);
			ftp.login(user, pw);
			// 원하시는 파일 타입
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			// 제대로 연결이 안댔을 경우 ftp접속을 끊습니다.
			if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
				ftp.disconnect();
				System.out.println("연결 실패");
			} else {
				System.out.println("연결 성공");
			}
			// 파일을 넣을 디렉토리를 설정해줍니다.
			ftp.mkd("/public_html/byeolsolResort/event");
			ftp.mkd("/public_html/byeolsolResort/event/event_" + eventId + "_thumbnail");
			System.out.println("성공?");
			// makeDirectory는 directory 생성이 필요할 때만 해주시면 됩니다.
			ftp.changeWorkingDirectory("/public_html/byeolsolResort/event/event_" + eventId + "_thumbnail");
			// 그 후 이전에 File로 변환한 업로드파일을 읽어 FTP로 전송합니다.
			FileInputStream fis = new FileInputStream(file);
			boolean isSucess = ftp.storeFile(uploadFile.getOriginalFilename(), fis);
			if (isSucess) {
				System.out.println("Thumb 성공");
			} else {
				System.out.println("실패");
			}
			fis.close();
			fos.close();
			System.out.println(file.exists());
			System.out.println(file.delete());
			// storeFile Method는 파일 송신결과를 boolean값으로 리턴합니다
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (ftp != null && ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	public boolean ftpDeleteEventImgReal(String imgPath, int eventId) {
		System.out.println(eventId);
		System.out.println("삭제");
		FTPClient ftp = null;
		try {
			ftp = new FTPClient();
			ftp.setControlEncoding("utf-8");
			ftp.connect(server, port);
			ftp.login(user, pw);
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
				ftp.disconnect();
				System.out.println("연결 실패");
			} else {
				System.out.println("연결 성공");
			}
			// 파일을 갖고 있는지 가지고 있지 않은지 확인후
			boolean isHave = false;
			FTPFile[] ftpFiles = ftp.listDirectories("/public_html/byeolsolResort/event");
			for (FTPFile ftpFile : ftpFiles) {
				System.out.println("폴더 경로 : " + ftpFile.getName());
				if (ftpFile.getName().equals("event_" + eventId + "_thumbnail")) {
					System.out.println(ftpFile.getName());
					isHave = true;
					break;
				}
			}
			if (isHave) {
				ftp.changeWorkingDirectory("/public_html/byeolsolResort/event/event_" + eventId + "_thumbnail");
				String deletePath = "/public_html/byeolsolResort/event/event_" + eventId + "_thumbnail"
						+ imgPath.substring(imgPath.lastIndexOf('/'), imgPath.length());
				System.out.println("deletePath : " + deletePath);
				// 가지고 있다면 삭제
				if (ftp.deleteFile(deletePath)) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (ftp != null && ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	public boolean ftpDeleteEventImg(String imgPath, int eventId) {
		System.out.println(eventId);
		System.out.println("삭제");
		FTPClient ftp = null;
		try {
			ftp = new FTPClient();
			ftp.setControlEncoding("utf-8");
			ftp.connect(server, port);
			ftp.login(user, pw);
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
				ftp.disconnect();
				System.out.println("연결 실패");
			} else {
				System.out.println("연결 성공");
			}
			boolean isHave = false;
			FTPFile[] ftpFiles = ftp.listDirectories("/public_html/byeolsolResort/event");
			for (FTPFile ftpFile : ftpFiles) {
				System.out.println("폴더 경로 : " + ftpFile.getName());
				if (ftpFile.getName().equals("event_" + eventId + "_thumbnail")) {
					System.out.println(ftpFile.getName());
					isHave = true;
					break;
				}
			}
			if (isHave) {
				ftp.changeWorkingDirectory("/public_html/byeolsolResort/event/event_" + eventId + "_thumbnail");
				String deletePath = "/public_html/byeolsolResort/event/event_" + eventId + "_thumbnail"
						+ imgPath.substring(imgPath.lastIndexOf('/'), imgPath.length());

				if (ftp.deleteFile(deletePath)) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (ftp != null && ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	// 홈페이지에 있는 이미지 변경
	public void ftpAdminImg(MultipartFile uploadFile, String classification, String value) {
		FTPClient ftp = null;
		String type = "";
		String oName = uploadFile.getOriginalFilename();
		String fileName = "";
		File tempDirectory = new File(System.getProperty("java.io.tmpdir"));
		System.out.println(tempDirectory.getAbsolutePath());
		try {
			File file = new File(tempDirectory.getAbsolutePath() + "/" + uploadFile.getOriginalFilename());
			if (file.createNewFile()) {
				System.out.println("생성");
			}
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(uploadFile.getBytes());
			fos.close();
			// FTPClient를 생성합니다.
			ftp = new FTPClient();
			// 원하시는 인코딩 타입
			ftp.setControlEncoding("utf-8");
			ftp.connect(server, port);
			ftp.login(user, pw);
			// 원하시는 파일 타입
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			// 제대로 연결이 안댔을 경우 ftp접속을 끊습니다.
			if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
				ftp.disconnect();
				System.out.println("연결 실패");
			} else {
				System.out.println("연결 성공");
			}
			// 파일을 넣을 디렉토리를 설정해줍니다.
			ftp.mkd("/public_html/byeolsolResort/" + classification);
			System.out.println("성공?");
			// makeDirectory는 directory 생성이 필요할 때만 해주시면 됩니다.
			ftp.changeWorkingDirectory("/public_html/byeolsolResort/" + classification);
			FTPFile[] ftpFiles = ftp.listFiles();
			// 그 후 이전에 File로 변환한 업로드파일을 읽어 FTP로 전송합니다.
			System.out.println(classification);
			System.out.println(value);
			System.out.println(ftpFiles.length);
			for (FTPFile ftpFile : ftpFiles) {
				System.out.println(ftpFile.getName().lastIndexOf('.'));
//				System.out.println("자름 : " + ftpFile.getName().substring(0, ftpFile.getName().lastIndexOf('.')-1));
				if (ftpFile.getName().substring(0, ftpFile.getName().lastIndexOf('.')).equals(value)) {
					System.out.println("aaa");
					fileName = ftpFile.getName().substring(0, ftpFile.getName().lastIndexOf('.'));
					type = ftpFile.getName().substring(ftpFile.getName().lastIndexOf('.'), ftpFile.getName().length());
				}
			}
			System.out.println(ftpFiles.length);
			FileInputStream fis = new FileInputStream(file);
			System.out.println(fileName + type);
			System.out.println("dump" + fileName + ftpFiles.length + type);
			System.out.println(ftp.rename(fileName + type, "dump" + fileName + ftpFiles.length + type));
			int random = (int)(Math.random()*500)+1;
			boolean isSucess = ftp.storeFile(value + type, fis);
			if (isSucess) {
				System.out.println("성공");
			} else {
				System.out.println("실패");
			}
			fis.close();
			fos.close();
			System.out.println(file.exists());
			System.out.println(file.delete());
			// storeFile Method는 파일 송신결과를 boolean값으로 리턴합니다
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ftp != null && ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 이름 변경
	public void ftpAdminImgRename(String classification, String value, String dumpImg) {
		// TODO Auto-generated method stub

		FTPClient ftp = null;
		String type = "";
		String fileName = "";
		String selectFileName = dumpImg.substring(dumpImg.lastIndexOf('/') + 1, dumpImg.length());
		File tempDirectory = new File(System.getProperty("java.io.tmpdir"));
		System.out.println(tempDirectory.getAbsolutePath());
		try {
			// FTPClient를 생성합니다.
			ftp = new FTPClient();
			// 원하시는 인코딩 타입
			ftp.setControlEncoding("utf-8");
			ftp.connect(server, port);
			ftp.login(user, pw);
			// 원하시는 파일 타입
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			// 제대로 연결이 안댔을 경우 ftp접속을 끊습니다.
			if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
				ftp.disconnect();
				System.out.println("연결 실패");
			} else {
				System.out.println("연결 성공");
			}
			// 파일을 넣을 디렉토리를 설정해줍니다.
			ftp.mkd("/public_html/byeolsolResort/" + classification);
			System.out.println("성공?");
			// makeDirectory는 directory 생성이 필요할 때만 해주시면 됩니다.
			ftp.changeWorkingDirectory("/public_html/byeolsolResort/" + classification);
			FTPFile[] ftpFiles = ftp.listFiles();
			// 그 후 이전에 File로 변환한 업로드파일을 읽어 FTP로 전송합니다.
			System.out.println("classification : " + classification);
			System.out.println("value : " + value);
			for (FTPFile ftpFile : ftpFiles) {
				System.out.println("자름 : " + ftpFile.getName().substring(0, ftpFile.getName().lastIndexOf('.')));
				if (ftpFile.getName().substring(0, ftpFile.getName().lastIndexOf('.')).equals(value)) {
					fileName = ftpFile.getName().substring(0, ftpFile.getName().lastIndexOf('.'));
					type = ftpFile.getName().substring(ftpFile.getName().lastIndexOf('.'), ftpFile.getName().length());
				}
			}
			System.out.println(fileName + type);
			int random = (int) (Math.random() * 500) + 15;
			System.out.println("바꾸기 전 이름 : " + fileName + type + "  바꿀 이름   :  dump" + fileName + random + type);
			boolean isSucess = ftp.rename(fileName + type, "dump" + fileName + random + type);
			if (isSucess) {
				System.out.println("이름 변경 성공");
			} else {
				System.out.println("이름 변경 실패");
			}
			System.out.println("바꾸기 전 이름 : " + selectFileName + "바꾼 후 이름 : " + value + type);
			boolean isMainSucess = ftp.rename(selectFileName, value + type);
			System.out.println("선택한 파일을 main으로 바꿈? :" + isMainSucess);
			// storeFile Method는 파일 송신결과를 boolean값으로 리턴합니다
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ftp != null && ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

}