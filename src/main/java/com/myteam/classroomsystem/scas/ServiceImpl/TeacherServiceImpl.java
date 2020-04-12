package com.myteam.classroomsystem.scas.ServiceImpl;

import com.myteam.classroomsystem.scas.Entity.ItemForteacher;
import com.myteam.classroomsystem.scas.Entity.Teacher;
import com.myteam.classroomsystem.scas.Entity.TeacherForSeacher;
import com.myteam.classroomsystem.scas.mapper.TeacherMepper;
import com.myteam.classroomsystem.scas.service.TeacherService;
import net.sf.json.JSONArray;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherMepper teacherMepper;
    private static List<TeacherForSeacher> teacherList = new ArrayList<>();
    private static int lenForTeacher = 0;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public List<TeacherForSeacher> findAllTeacherByDep() {
        if (lenForTeacher == 0) {
            String teacherStrings = null;
            teacherStrings = redisTemplate.opsForValue().get("teachers").toString();
            System.out.println(teacherStrings);
            //先到redis看是否有缓存
            if (teacherStrings.length() != 0) {
                com.alibaba.fastjson.JSONArray jsonArray = com.alibaba.fastjson.JSONArray.parseArray(teacherStrings);
                teacherList = com.alibaba.fastjson.JSONArray.parseArray(jsonArray.toJSONString(), TeacherForSeacher.class);
                System.out.println(teacherList);
            } else {
                //再到mysql获取
                List<Teacher> teachers = teacherMepper.findAllTeacher();
                for (Teacher teacher : teachers) {
                    String surname = convertSingleHanzi2Pinyin(teacher.getName());
                    TeacherForSeacher teacherForSeacher = new TeacherForSeacher();
                    teacherForSeacher.setTitle(surname);
                    ItemForteacher itemForteacher = new ItemForteacher();
                    itemForteacher.setName(teacher.getName());
                    itemForteacher.setKey(surname);
                    itemForteacher.setTid(teacher.getTid());
                    List<ItemForteacher> itemForteachersList = new ArrayList<>();
                    itemForteachersList.add(itemForteacher);
                    teacherForSeacher.setItem(itemForteachersList);
                    teacherList.add(teacherForSeacher);
                }
                JSONArray jsonObject = JSONArray.fromObject(teacherList);
                String str = jsonObject.toString();
                //缓存到redis
                redisTemplate.opsForValue().set("teachers", str);
                lenForTeacher = teacherList.size();
            }
        }
        return teacherList;
    }

    /*  *
     * @Description: 根据姓名生成姓氏首字母
     * @Param: [string]
     * @return: java.lang.String
     * @Author: king
     * @Date: 2020/4/12
     */
    private String convertSingleHanzi2Pinyin(String string) {
        char hanzi = string.charAt(0);
        HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
        outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        String[] res;
        StringBuffer sb = new StringBuffer();
        try {
            res = PinyinHelper.toHanyuPinyinStringArray(hanzi, outputFormat);
            sb.append(res[0]);// 对于多音字，只用第一个拼音
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        String pinying = sb.toString();
        pinying = pinying.substring(0, 1);
        pinying = pinying.toUpperCase();
        return pinying;
    }

    @Test
    public void test() {

    }
}
