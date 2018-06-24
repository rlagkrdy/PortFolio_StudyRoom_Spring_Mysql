package kr.co.hakyo.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageUtil {
	public Map<String, Object> imageUpload(MultipartFile file, Long USR_KEY, String MEDIA_REF_CATE) {
		
		
		try {
			String MEDIA_EXT = FilenameUtils.getExtension(file.getOriginalFilename());
			String MEDIA_ORI_NAME = file.getName();
			String MEDIA_NAME = UUID.randomUUID() + "." + MEDIA_EXT;

			makeDir(Constants.directoryPath);

			File path = new File(Constants.directoryPath, MEDIA_NAME);
			file.transferTo(path);
			
			int MEDIA_WIDTH = 0;
			int MEIDA_HEIGHT= 0;
			
			BufferedImage image = ImageIO.read(new File(Constants.directoryPath, MEDIA_NAME));
			MEDIA_WIDTH = image.getWidth();
			MEIDA_HEIGHT = image.getHeight();

			String MEDIA_URL = Constants.Url + MEDIA_NAME;
			String MEDIA_ORI_URL = path.getAbsolutePath();
			return setMediaObj(USR_KEY, MEDIA_REF_CATE, MEDIA_ORI_NAME, MEDIA_NAME, MEDIA_WIDTH, MEIDA_HEIGHT,
					MEDIA_EXT, MEDIA_URL, MEDIA_ORI_URL);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	private void makeDir(String directoryPath) {
		File directory = new File(directoryPath);
		if (!directory.exists()) {
			directory.mkdirs();
			System.err.println("[폴더생성] : " + directoryPath);
		}
	}

	private Map<String, Object> setMediaObj(Long MEDIA_REF_KEY, String MEDIA_REF_CATE, String MEDIA_ORI_NAME,
			String MEDIA_NAME, int MEDIA_WIDTH, int MEIDA_HEIGHT, String MEDIA_EXT, String MEDIA_URL,
			String MEDIA_ORI_URL) {
		HashMap<String, Object> mediaData = new HashMap<String, Object>();
		mediaData.put("MEDIA_REF_KEY", MEDIA_REF_KEY);
		mediaData.put("MEDIA_REF_CATE", MEDIA_REF_CATE);
		mediaData.put("MEDIA_ORI_NAME", MEDIA_ORI_NAME);
		mediaData.put("MEDIA_NAME", MEDIA_NAME);
		mediaData.put("MEDIA_WIDTH", MEDIA_WIDTH);
		mediaData.put("MEDIA_HEIGHT", MEIDA_HEIGHT);
		mediaData.put("MEDIA_EXT", MEDIA_EXT);
		mediaData.put("MEDIA_URL", MEDIA_URL);
		mediaData.put("MEDIA_ORI_URL", MEDIA_ORI_URL);
		return mediaData;
	}
	
	public boolean deleteFile(File file) {
		if (file.exists())
			return (file.delete());
		else
			return false;
	}
}
