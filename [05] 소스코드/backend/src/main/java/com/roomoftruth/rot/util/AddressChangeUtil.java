package com.roomoftruth.rot.util;

import com.roomoftruth.rot.domain.User;
import com.roomoftruth.rot.dto.UserFirstSaveRequestDto;
import com.roomoftruth.rot.repository.InterestRepository;
import com.roomoftruth.rot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

public class AddressChangeUtil {

	public String addressChange(String sd) {

		if(sd.equals("서울")) {
			return "서울특별시";
		}
		if(sd.equals("인천")) {
			return "인천광역시";
		}
		if(sd.equals("부산")) {
			return "부산광역시";
		}
		if(sd.equals("대구")) {
			return "대구광역시";
		}
		if(sd.equals("대전")) {
			return "대전광역시";
		}
		if(sd.equals("광주")) {
			return "광주광역시";
		}
		if(sd.equals("울산")) {
			return "울산광역시";
		}
		if(sd.equals("강원")) {
			return "강원도";
		}
		if(sd.equals("경기")) {
			return "경기도";
		}
		if(sd.equals("경남")) {
			return "경상남도";
		}
		if(sd.equals("경북")) {
			return "경상북도";
		}
		if(sd.equals("전북")) {
			return "전라북도";
		}
		if(sd.equals("전남")) {
			return "전라남도";
		}
		if(sd.equals("충남")) {
			return "충청남도";
		}
		if(sd.equals("충북")) {
			return "충청북도";
		}
		if(sd.equals("세종특별자치시")) {
			return "세종특별자치시";
		}
		else {
			return "제주특별자치도";
		}
	}

}
