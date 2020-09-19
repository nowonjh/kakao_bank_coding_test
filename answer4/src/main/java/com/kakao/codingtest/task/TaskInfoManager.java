package com.kakao.codingtest.task;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakao.codingtest.config.vo.TaskInfoVO;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yuganji
 * 1분에 한번씩 테스크 설정파일을 읽어 캐싱한다.
 */
@Slf4j
@Service
public class TaskInfoManager {
	private ObjectMapper objMapper = new ObjectMapper();

	@Getter
	private List<TaskInfoVO> taskList = null;

	@PostConstruct
	public void init() {
		this.reload();
	}
	@Scheduled(fixedDelay = 60 * 1000)
	public void reload() {
		try {
			this.taskList = objMapper.readValue(new File("./conf/task_info.json"),
					new TypeReference<List<TaskInfoVO>>(){});
		} catch (IOException e) {
			if (this.taskList == null) {
				log.error(e.getMessage(), e);
				System.exit(0);
			}
			log.warn("Didn't reload configuration.json.", e);
		}
	}
}
