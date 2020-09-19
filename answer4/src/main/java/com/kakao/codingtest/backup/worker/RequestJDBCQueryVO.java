package com.kakao.codingtest.backup.worker;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RequestJDBCQueryVO {
	private String timeField;
	private String tableName;
	private String startTime;
	private String endTime;
}
