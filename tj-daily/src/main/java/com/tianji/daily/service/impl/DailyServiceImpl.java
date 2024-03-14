package com.tianji.daily.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tianji.common.domain.dto.PageDTO;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import com.tianji.common.exceptions.BadRequestException;
import com.tianji.common.utils.BeanUtils;
import com.tianji.daily.domain.dto.DailyDTO;
import com.tianji.daily.domain.po.Daily;
import com.tianji.daily.domain.po.DailyDetail;
import com.tianji.daily.domain.query.DailyCalQuery;
import com.tianji.daily.domain.query.DailyPageQuery;
import com.tianji.daily.domain.vo.DailyCalVO;
import com.tianji.daily.domain.vo.DailyVO;
import com.tianji.daily.mapper.DailyMapper;
import com.tianji.daily.service.IDailyDetailService;
import com.tianji.daily.service.IDailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tianji.daily.enums.DateType;
import org.springframework.transaction.annotation.Transactional;
import static com.tianji.daily.contants.DailyConstants.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    private DailyMapper dailyMapper;

    @Override
    @Transactional
    public void saveDaily(DailyDTO dailyDTO) {

        LocalDate date = LocalDate.parse((dailyDTO.getDate()));
        Integer existingDaily = getBaseMapper().findByDate(date);
        if (existingDaily != null) {
            // 如果存在，则抛出异常
            throw new  BadRequestException("该日期已存在记录,请维护已有记录");
        }
        DateType type = DateType.of(dailyDTO.getDatetype());
        Daily daily = new Daily();
        daily.setDate(date);
        daily.setName(dailyDTO.getName());
        daily.setUsername(dailyDTO.getUsername());
        daily.setDatetype(type);
        daily.setOndutytime(dailyDTO.getOndutytime());
        daily.setOffdutytime(dailyDTO.getOffdutytime());
        daily.setRdmno1(dailyDTO.getRdmno1());
        daily.setRdmno2(dailyDTO.getRdmno2());
        daily.setRdmno3(dailyDTO.getRdmno3());
        Double worklength = dailyDTO.getWorklength();
        daily.setWorklength(worklength);
        daily.setNote(dailyDTO.getNote());
        save(daily);

        if (dailyDTO.getDatetype() != 2)
    {
        saveDetail(dailyDTO,daily.getId());
    }


//        List<String> rdms = new ArrayList<String>();
//        String rdmNo1 = dailyDTO.getRdmno1();
//        if (rdmNo1 != null && !rdmNo1.trim().isEmpty()){rdms.add(rdmNo1);}
//        String rdmNo2 = dailyDTO.getRdmno2();
//        if (rdmNo2!= null && !rdmNo2.trim().isEmpty()){rdms.add(rdmNo2);}
//        String rdmNo3 = dailyDTO.getRdmno3();
//        if (rdmNo3!= null && !rdmNo3.trim().isEmpty()){rdms.add(rdmNo3);}
//
//        System.out.println(worklength+" "+rdms.size());
//        String avgLen = String.valueOf(worklength / rdms.size());
//
//        List<DailyDetail> dailyDetails = new ArrayList<>();
//        for (String rdm : rdms) {
//            DailyDetail dailyDetail = BeanUtils.toBean(dailyDTO, DailyDetail.class);
//            dailyDetail.setId(daily.getId());
//            dailyDetail.setRdmno(rdm);
//            dailyDetail.setRdmnoLen(avgLen);
//            dailyDetails.add(dailyDetail);
//        }
//        dailyDetailService.saveBatch(dailyDetails);

    }

    public void saveDetail(DailyDTO dailyDTO, int Id)
    {
        List<String> rdms = new ArrayList<String>();
        String rdmNo1 = dailyDTO.getRdmno1();
        if (rdmNo1 != null && !rdmNo1.trim().isEmpty()){rdms.add(rdmNo1);}
        String rdmNo2 = dailyDTO.getRdmno2();
        if (rdmNo2!= null && !rdmNo2.trim().isEmpty()){rdms.add(rdmNo2);}
        String rdmNo3 = dailyDTO.getRdmno3();
        if (rdmNo3!= null && !rdmNo3.trim().isEmpty()){rdms.add(rdmNo3);}

//        System.out.println(worklength+" "+rdms.size());
        String avgLen = String.valueOf(dailyDTO.getWorklength() / rdms.size());

        List<DailyDetail> dailyDetails = new ArrayList<>();
        for (String rdm : rdms) {
            DailyDetail dailyDetail = BeanUtils.toBean(dailyDTO, DailyDetail.class);
            dailyDetail.setId(Id);
            dailyDetail.setRdmno(rdm);
            dailyDetail.setRdmnoLen(avgLen);
            dailyDetails.add(dailyDetail);
        }
        dailyDetailService.saveBatch(dailyDetails);
    }

    @Override
    public PageDTO<DailyVO> queryDailyPage(DailyPageQuery query) {
        String name = query.getName();
        String rdmno = query.getRdmno();
        LocalDate date = query.getDate();

        Page<Daily> p = query.toMpPageDefaultSortByCreateTimeDesc();

        LambdaQueryWrapper<Daily> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(name!=null,Daily::getName,name);
//        if(rdmno!=null)
//        {
        wrapper.and(wq  -> wq.eq(rdmno!=null,Daily::getRdmno1,rdmno).
                or().eq(rdmno!=null,Daily::getRdmno2,rdmno).
                or().eq(rdmno!=null,Daily::getRdmno3,rdmno));
//        }
        wrapper.eq(date!=null,Daily::getDate,date);

        p = dailyMapper.selectPage(p,wrapper);

        return PageDTO.of(p, u -> {
            DateType type = u.getDatetype();
            DailyVO v = BeanUtils.toBean(u, DailyVO.class);
            switch (type) {
                case WORKDAY:
                    v.setDatetype(WORK_DAY);
                    break;
                case DAYOFF:
                    v.setDatetype(DAY_OFF);
                    break;
                case REST:
                    v.setDatetype(REST);
                    break;

                default:
                    break;
            }
            return v;
        });
        }

    @Override
    public Daily queryById(int id) {

        Daily daily =  getBaseMapper().queryById(id);
        System.out.println(daily);
        return daily;
    }

    @Override
    @Transactional
    public void updateDaily(DailyDTO dailyDTO) {
        DateType type = DateType.of(dailyDTO.getDatetype());
        Daily daily = BeanUtils.toBean(dailyDTO,Daily.class);
        daily.setDatetype(type);
        updateById(daily);
        // 2.修改详情

        int id = dailyDTO.getId();
        dailyDetailService.removeById(id);
        saveDetail(dailyDTO,id);

    }

    @Override
    public DailyCalVO DailyCalculator(DailyCalQuery query) {
        String name = query.getName();
        String username = query.getUsername();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startdate = LocalDate.parse(query.getStartdate(), formatter);
        LocalDate enddate = LocalDate.parse(query.getEnddate(), formatter);
//        LocalDate startdate = LocalDate.of(2024, 1, 1);
//        LocalDate enddate = LocalDate.of(2024, 12, 1);
        Integer dayoffnums = query.getDayoffnums();

        DailyCalVO vo = new DailyCalVO();
        vo.setName(name);
        vo.setUsername(username);

        Double totaldays = dailyMapper.querytotaldays(name,username,startdate,enddate);
        Double standarddays = (double) ChronoUnit.DAYS.between(startdate, enddate)+1-dayoffnums;
        Double leftdays = totaldays-standarddays;

        if (leftdays>0)
        {
            Double offdayrecords = dailyMapper.queryoffdayrecords(name,username,startdate,enddate);
            if(offdayrecords<=leftdays){
                Double offdayot = offdayrecords;
                Double workdayot = leftdays-offdayot;
                //抵消后的加班非工作日和工作日加班天数
                vo.setOffdayot(offdayot);
                vo.setWorkdayot(workdayot);
            }
            else{
                Double offdayot = leftdays;
                Double workdayot = 0.0;
                //抵消后的加班非工作日和工作日加班天数
                vo.setOffdayot(offdayot);
                vo.setWorkdayot(workdayot);
            }

        }



        vo.setTotledays(totaldays);
        vo.setStandarddays(standarddays);
        vo.setLeftdays(leftdays);
        return vo;
    }


}
