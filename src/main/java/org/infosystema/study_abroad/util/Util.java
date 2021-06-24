package org.infosystema.study_abroad.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.SystemException;

import org.infosystema.study_abroad.dto.AttachmentBinaryDTO;
import org.infosystema.study_abroad.model.Attachment;

public class Util {
	
	public static List<AttachmentBinaryDTO> getFiles(Set<Attachment> attachments) {
		List<AttachmentBinaryDTO> list = new ArrayList<>();
		for (Attachment attachment : attachments) {
			try {
				list.add(createAttachmentDTO(attachment));
			} catch (SystemException e) {
				e.printStackTrace();
				continue;
			}
		}
		return list;
	}
	
	public static List<AttachmentBinaryDTO> getFile(Attachment attachment) {
		AttachmentBinaryDTO dto=null;
		List<AttachmentBinaryDTO> list = new ArrayList<>();
			try {
				dto=createAttachmentDTO(attachment);
				list.add(dto);
			} catch (SystemException e) {
				e.printStackTrace();
			
			}
			
		return list;
	}
	
	public static AttachmentBinaryDTO createAttachmentDTO(Attachment attachment) throws SystemException {

		try {
			AttachmentBinaryDTO attachmentBinaryDTO = new AttachmentBinaryDTO();
			attachmentBinaryDTO.setName(attachment.getFileName());
			attachmentBinaryDTO.setRepositoryName(attachment.getRepositoryLink());
			attachmentBinaryDTO.setAttachment(attachment);
			
			return attachmentBinaryDTO;
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}
	
	public static List<Field> getAllFields(Class<?> clazz) {
        if (clazz == null) {
            return Collections.emptyList();
        }

        List<Field> result = new ArrayList<>(getAllFields(clazz.getSuperclass()));
        List<Field> filteredFields = Arrays.stream(clazz.getDeclaredFields())
                .collect(Collectors.toList());
        result.addAll(filteredFields);
        return result;
    }

	public static String dateToString(Date dateToConvert) {
        return dateToConvert.toString();
    }
}
