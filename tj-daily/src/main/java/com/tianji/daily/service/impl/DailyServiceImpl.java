package com.tianji.daily.service.impl;

import com.tianji.common.domain.dto.PageDTO;
import com.tianji.common.utils.BeanUtils;
import com.tianji.daily.domain.dto.DailyDTO;
import com.tianji.daily.domain.po.Daily;
import com.tianji.daily.domain.po.DailyDetail;
import com.tianji.daily.domain.query.DailyPageQuery;
import com.tianji.daily.domain.vo.DailyVO;
import com.tianji.daily.mapper.DailyMapper;
import com.tianji.daily.service.IDailyDetailService;
import com.tianji.daily.service.IDailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tianji.daily.enums.DateType;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author rio
 * @since 2024-03-02
 */
@Service
public class DailyServiceImpl extends ServiceImpl<DailyMapper, Daily> implements IDailyService {

    @Autowired
    private IDailyDetailService dailyDetailService;

    @Override
    @Transactional
    public void saveDaily(DailyDTO dailyDTO) {
        DateType type = DateType.of(dailyDTO.getDatetype());
        Daily daily = new Daily();
        daily.setDate(LocalDate.parse((dailyDTO.getDate())));
        daily.setName(dailyDTO.getName());
        daily.setUsername(dailyDTO.getUsername());
        daily.setDatetype(type);
        daily.setOndutytime(dailyDTO.getOndutytime());
        daily.setOffdutytime(dailyDTO.getOffdutytime());
        daily.setRdmno1(dailyDTO.getRdmNo1());
        daily.setRdmno2(dailyDTO.getRdmNo2());
        daily.setRdmno3(dailyDTO.getRdmNo3());
        Double worklength = dailyDTO.getWorklength();
        daily.setWorklength(worklength);
        save(daily);

        List<String> rdms = new ArrayList<String>();
        String rdmNo1 = dailyDTO.getRdmNo1();
        if (rdmNo1 != null && !rdmNo1.trim().isEmpty()){rdms.add(rdmNo1);}
        String rdmNo2 = dailyDTO.getRdmNo2();
        if (rdmNo2!= null && !rdmNo2.trim().isEmpty()){rdms.add(rdmNo2);}
        String rdmNo3 = dailyDTO.getRdmNo3();
        if (rdmNo3!= null && !rdmNo3.trim().isEmpty()){rdms.add(rdmNo3);}

        System.out.println(worklength+" "+rdms.size());
        String avgLen = String.valueOf(worklength / rdms.size());

        List<DailyDetail> dailyDetails = new ArrayList<>();
        for (String rdm : rdms) {
            DailyDetail dailyDetail = BeanUtils.toBean(dailyDTO, DailyDetail.class);
            dailyDetail.setId(daily.getId());
            dailyDetail.setRdmno(rdm);
            dailyDetail.setRdmnoLen(avgLen);
            dailyDetails.add(dailyDetail);
        }
        dailyDetailService.saveBatch(dailyDetails);


    }

    @Override
    public PageDTO<DailyVO> queryDailyPage(DailyPageQuery query) {

        return null;
    }


}
