package com.mc.core.infra.util;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.mc.core.infra.exception.exceptions.RestApiException;
import com.mc.core.infra.response.ResponseCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

// 파일 업로드 및 관리 유틸리티 클래스
@Component
public class FileUtil {

	@Value("${upload.path}") // 업로드 경로 설정
	private String filePath;

	// 파일 업로드 메서드
	public List<FileDTO> upload(List<MultipartFile> files) {
		List<FileDTO> fileData = new ArrayList<FileDTO>();

		if(files == null) return fileData; // 파일 리스트가 null이면 빈 리스트 반환

		if(!files.isEmpty() && !Objects.equals(files.get(0).getOriginalFilename(), "")) {
			for(MultipartFile mf : files) {
				String savePath = generateSavePath(); // 저장 경로 생성
				String originFileName = mf.getOriginalFilename(); // 원본 파일 이름
				String renameFileName = getRenameFileName(originFileName); // 변경된 파일 이름 생성

				FileDTO fileDTO = new FileDTO();
				fileDTO.setOriginFileName(originFileName); // 원본 파일 이름 설정
				fileDTO.setRenameFileName(renameFileName); // 변경된 파일 이름 설정
				fileDTO.setSavePath(savePath); // 저장 경로 설정

				fileData.add(fileDTO); // 파일 데이터 리스트에 추가
				saveFile(mf, fileDTO); // 파일 저장 메서드 호출
			}
		}
		return fileData;
	}

	// 저장 경로 생성 메서드
	private String generateSavePath() {
		LocalDate now = LocalDate.now(); // 현재 날짜 가져오기
		return filePath + now.getYear() + "/" + now.getMonthValue() + "/" + now.getDayOfMonth() + "/";
	}

	// 변경된 파일 이름 생성 메서드
	private String getRenameFileName(String originFileName) {
		UUID renameFileID = UUID.randomUUID(); // 랜덤 UUID 생성
	    return renameFileID.toString() + originFileName.substring(originFileName.lastIndexOf(".")); // 확장자 유지
	}

	// 파일 저장 메서드
	private void saveFile(MultipartFile mf, FileDTO dto)  {
		File path = new File(dto.getSavePath()); // 저장 경로의 파일 객체 생성
		if(!path.exists()) {
			path.mkdirs(); // 경로가 존재하지 않으면 디렉토리 생성
		}

		File tempFile = new File(dto.getSavePath() + dto.getRenameFileName()); // 최종 파일 객체 생성

		try {
			mf.transferTo(tempFile); // 파일 전송
		} catch (IllegalStateException | IOException e) {
			throw new RestApiException(ResponseCode.FILE_ERROR); // 예외 발생 시 사용자 정의 예외 던짐
		}
	}

	// 파일 삭제 메서드
	public void deleteFile(String fullPath, String fileName) {
		File file = new File(fullPath + fileName);
		file.delete(); // 파일 삭제
	}
}
