package web.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import web.dto.Boardfile;

public class DownloadView extends AbstractView{
	
	private static final Logger logger = LoggerFactory.getLogger(DownloadView.class);

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	
		//모델값 가져오기 - Filetest 객체 (downFile)
		Boardfile file = (Boardfile) model.get("downFile");
		logger.info(file.toString());
		
		//서블릿컨텍스트 얻기
		ServletContext context = request.getSession().getServletContext();
		
		//업로드된 파일 객체 얻기
		File src = new File(
				context.getRealPath("upload"),
				file.getStoredname());
		logger.info(src.toString()); //C:\eclipse\.. 이렇게 realpath
	
		//ContentType 설정
		// 응답데이터 혀익으로 이진데이터를 설정함
		// 브라우저는 마땅한 형식이 없는 데이터라고 생각하고 파일로 다운받아서 처리
		response.setContentType("application/octet-stream");
		
		//응답 데이터(파일)의 크기 설정
		response.setContentLength((int)src.length());
		
		//인코딩 설정
		response.setCharacterEncoding("utf-8");
		
		
		//클라이언트가 저장할 때 사용할 파일의 이름 인코딩 설정
		String filename = URLEncoder.encode(file.getOriginname(), "utf-8");
		
		//UTF-8 인코딩 오류 수정 (한글만 바꿔야 하는데 특수기호까지 바꿔서 오류남)
		filename = filename.replace("+", "%20"); //띄어쓰기
		
		//브라우저가 다운로드에 사용하는 이름 설정하기
		response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\""); 
		
		
		//서버의 File -> FileInputstream으로 읽고 
		//	-> Response의 Outputstream으로 출력
		
		//서버에 저장된 파일 객체
		File origin = new File(
				context.getRealPath("upload"),
				file.getStoredname() );
		
		//서버의 파일 입력 스트림
		FileInputStream fis = new FileInputStream(origin);
		
		//서버의 응답 출력스트림
		OutputStream out = response.getOutputStream();
		
		//서버->클라이언트 복사
		FileCopyUtils.copy(fis, out);
		
		out.flush(); //출력스트림 버퍼 비우기
		
		//스트림 닫기
		fis.close();
		out.close();
	}
	
}
