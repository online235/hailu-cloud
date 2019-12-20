package com.hailu.cloud.api.admin.module.xinan.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hailu.cloud.api.admin.module.xinan.dao.XaHelpMemberMapper;
import com.hailu.cloud.api.admin.module.xinan.entity.Helppictures;
import com.hailu.cloud.api.admin.module.xinan.entity.XaHelpMember;
import com.hailu.cloud.api.admin.module.xinan.model.XaHelpMemberDetailModel;
import com.hailu.cloud.api.admin.module.xinan.model.XaHelpMemberModel;
import com.hailu.cloud.api.admin.module.xinan.service.XaHelpMenberService;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import com.hailu.cloud.common.model.auth.AdminLoginInfoModel;
import com.hailu.cloud.common.model.auth.MerchantUserLoginInfoModel;
import com.hailu.cloud.common.model.page.PageInfoModel;
import com.hailu.cloud.common.utils.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

@Service
public class XaHelpMenberServiceImpl implements XaHelpMenberService {

    @Autowired
    private XaHelpMemberMapper xaHelpMemberMapper;
    @Autowired
    private BasicFeignClient uuidFeign;
    @Resource
    private HelpPicturesService helpPicturesService;


    @Override
    public List<XaHelpMemberModel> findListByParameter(Object parameter) {
        return xaHelpMemberMapper.findListByParameter(parameter);
    }

    @Override
    public Long insert(XaHelpMember xaHelpMember) {

        AdminLoginInfoModel adminLoginInfo = RequestUtils.getAdminLoginInfo();
        xaHelpMember.setId(uuidFeign.uuid().getData());
        xaHelpMember.setCreateTime(new Date());
        xaHelpMember.setUpdateTime(new Date());
        xaHelpMember.setMenberId(adminLoginInfo.getId());
        xaHelpMemberMapper.insert(xaHelpMember);
        return xaHelpMember.getId();
    }

    @Override
    public int update(XaHelpMember xaHelpMember) {

        xaHelpMember.setUpdateTime(new Date());
        return xaHelpMemberMapper.update(xaHelpMember);
    }


    /**
     * 查询信息列表分页
     */
    @Override
    public PageInfoModel<List<XaHelpMemberModel>> findListByParameterNewPage(Integer pageNum, Integer size, Object parameter) {

        Page pageData = PageHelper.startPage(pageNum, size);
        List<XaHelpMemberModel> result = xaHelpMemberMapper.findListByParameter(parameter);
        return new PageInfoModel<>(pageData.getPages(), pageData.getTotal(), result);
    }


    @Override
    public XaHelpMemberModel findXaHelpMemberModelById(Long id) {
        return xaHelpMemberMapper.findXaHelpMemberModelById(id);
    }


    /**
     * 获取救助详情，图片
     * @param xaHelpMemberId
     * @return
     */
    @Override
    public XaHelpMemberDetailModel getXaHelpMemberDetailModel(Long xaHelpMemberId) {

        XaHelpMemberDetailModel xaHelpMemberDetailModel = new XaHelpMemberDetailModel();
        List<String>  pictureImages = new ArrayList<>();
        List<String>  pictureHelpImages = new ArrayList<>();
        List<String>  pictureHelpVideos = new ArrayList<>();
        XaHelpMemberModel xaHelpMemberModel = this.findXaHelpMemberModelById(xaHelpMemberId);
        xaHelpMemberDetailModel.setXaHelpMemberModel(xaHelpMemberModel);
        List<Helppictures> helppicturesList = helpPicturesService.findHelpPicturesList(xaHelpMemberId);
        if(!CollectionUtils.isEmpty(helppicturesList)){
            for(Helppictures helppictures:helppicturesList){
                if(helppictures.getPictureType() == 1){
                    pictureImages.add(helppictures.getPicture());
                }else  if(helppictures.getPictureType() == 2){
                    pictureHelpImages.add(helppictures.getPicture());
                }else  if(helppictures.getPictureType() == 3){
                    pictureHelpVideos.add(helppictures.getPicture());
                }
            }
        }
        xaHelpMemberDetailModel.setXaHelpMemberModel(xaHelpMemberModel);
        xaHelpMemberDetailModel.setPictureHelpImages(pictureHelpImages);
        xaHelpMemberDetailModel.setPictureHelpVideos(pictureHelpVideos);
        xaHelpMemberDetailModel.setPictureImages(pictureImages);
        return xaHelpMemberDetailModel;
    }



}
