package com.hailu.cloud.api.mall.module.user.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hailu.cloud.api.mall.module.user.dao.InviteDetailMapper;
import com.hailu.cloud.api.mall.module.user.dao.MemberDetailMapper;
import com.hailu.cloud.api.mall.module.user.entity.MemberDetail;
import com.hailu.cloud.api.mall.module.user.service.IMemberDetailService;
import com.hailu.cloud.api.mall.module.user.vo.MemberDetailVo;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import com.hailu.cloud.common.model.page.PageInfoModel;
import com.hailu.cloud.common.utils.RequestUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author junpei.deng
 */
@Service
public class MemberDetailServiceImpl implements IMemberDetailService {

    @Resource
    private MemberDetailMapper memberDetailMapper;

    @Resource
    private BasicFeignClient basicFeignClient;

    @Resource
    private InviteDetailMapper inviteDetailMapper;


    @Override
    public Long addAddress(String userId, String provinceId, String cityId, String areaId, String address,Integer wantBuyType,String name,String phone){
        MemberDetail memberDetail = getByUserId(userId);

        memberDetail.setProvinceId(provinceId);
        memberDetail.setCityId(cityId);
        memberDetail.setAddress(address);
        memberDetail.setAreaId(areaId);
        memberDetail.setWantBuyType(wantBuyType);
        memberDetail.setName(name);
        memberDetail.setPhone(phone);

        saveEntity(memberDetail);
        return memberDetail.getId();
    }

    public MemberDetail getByUserId(String userId){
        MemberDetail memberDetail = memberDetailMapper.findByUserId(userId);
        if(memberDetail == null){
            memberDetail = new MemberDetail();
            memberDetail.setUserId(userId);
        }
        return memberDetail;
    }


    /**
     *  增加会员信息
     * @param userId                会员ID
     * @param totalConsumption      累计消费
     * @param inviteMemberNum       邀请会员数量
     * @param inviteMerchatNum      邀请商家数量
     * @param invitePartnersNum     邀请合伙人数量
     * @param salesPerformance      销售业绩
     */
    @Override
    public void addTotal(String userId, BigDecimal totalConsumption, Integer inviteMemberNum, Integer inviteMerchatNum, Integer invitePartnersNum, BigDecimal salesPerformance) {
        MemberDetail memberDetail = getByUserId(userId);

        //累计消费
        if(totalConsumption != null){
            BigDecimal totalConsumptionOrgin = memberDetail.getTotalConsumption();
            totalConsumptionOrgin = totalConsumptionOrgin == null ? BigDecimal.valueOf(0): totalConsumptionOrgin;
            memberDetail.setTotalConsumption(totalConsumptionOrgin.add(totalConsumption));
        }

        //邀请会员数量
        if(inviteMemberNum != null){
            Integer inviteMemberNumOrgin = memberDetail.getInviteMemberNum();
            inviteMemberNumOrgin = inviteMemberNumOrgin == null ? 0:inviteMemberNumOrgin;
            memberDetail.setInviteMemberNum(inviteMemberNum + inviteMemberNumOrgin);
        }

        //邀请商家数量
        if(inviteMerchatNum != null){
            Integer inviteMerchatNumOrgin = memberDetail.getInviteMerchatNum();
            inviteMerchatNumOrgin = inviteMerchatNumOrgin == null ? 0:inviteMerchatNumOrgin;
            memberDetail.setInviteMerchatNum(inviteMerchatNumOrgin + inviteMerchatNum);
        }

        //邀请合伙人数量
        if(invitePartnersNum != null){
            Integer invitePartnersNumOrgin = memberDetail.getInvitePartnersNum();
            invitePartnersNumOrgin = invitePartnersNumOrgin == null? 0:invitePartnersNumOrgin;
            memberDetail.setInvitePartnersNum(invitePartnersNumOrgin + invitePartnersNum);
        }

        //销售业绩
        if(salesPerformance != null){
            BigDecimal salesPerformanceOrgin = memberDetail.getSalesPerformance();
            salesPerformanceOrgin = salesPerformanceOrgin == null ? BigDecimal.valueOf(0):salesPerformanceOrgin;
            memberDetail.setSalesPerformance(salesPerformance.add(salesPerformanceOrgin));
        }

        saveEntity(memberDetail);

    }


    /**
     * 保存或修改
     * @param memberDetail
     * @return
     */
    private MemberDetail saveEntity(MemberDetail memberDetail){
        if(memberDetail.getId() == null){
           memberDetail.setId(basicFeignClient.uuid().getData());
           memberDetailMapper.insertSelective(memberDetail);
           return memberDetail;
        }
        memberDetailMapper.updateByPrimaryKeySelective(memberDetail);
        return memberDetail;
    }


    @Override
    public Object findServiceProvidersList() {
        return  memberDetailMapper.findServiceProvidersList(2, RequestUtils.getMemberLoginInfo().getUserId());
    }


    @Override
    public Object findMemberDetail(int page, int size, String value, int type,String userId,Integer memberAll) throws BusinessException {
        Page pageDate = PageHelper.startPage(page,size);
        List<MemberDetailVo> list = null;
        //查询服务商 或者销售
        if(type == 2 || type == 3){
            list = inviteDetailMapper.findDetailByType(value,type,userId);
        }else if(type == 0){
            //查询会员
            list = inviteDetailMapper.findMemberDetail(value,type,userId,memberAll);
        }else {
            throw new BusinessException("查询类型不合法！");
        }
        list.forEach(data->{
            String phone = data.getPhone();
            data.setPhone(phone.substring(0,3)+"****"+phone.substring((phone.length()-3),phone.length()));
        });
        return new PageInfoModel<>(pageDate.getPages(),pageDate.getTotal(),list);
    }
}
