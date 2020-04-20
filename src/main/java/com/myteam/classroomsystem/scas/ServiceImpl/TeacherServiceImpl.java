package com.myteam.classroomsystem.scas.ServiceImpl;

import com.myteam.classroomsystem.scas.Entity.ItemForteacher;
import com.myteam.classroomsystem.scas.Entity.Teacher;
import com.myteam.classroomsystem.scas.Entity.TeacherForSeacher;
import com.myteam.classroomsystem.scas.mapper.TeacherMepper;
import com.myteam.classroomsystem.scas.service.TeacherService;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherMepper teacherMepper;

    private static int lenForTeacher = 0;
    private static HashMap<String, List<ItemForteacher>> teacherHash = new HashMap<>();

    private static List<TeacherForSeacher> teacherList = new ArrayList<>();

    @Resource
    private RedisTemplate redisTemplate;

    /*  *
     * @Description: 获取所有的老师姓名，姓氏首字母
     * @Author: king
     * @Date: 2020/4/15
     */
    @Override
    public List<TeacherForSeacher> findAllTeacherByDep() {

        if (lenForTeacher == 0) {
            List<Teacher> teachers = teacherMepper.findAllTeacher();
            for (Teacher teacher : teachers) {
                String teacherName = teacher.getName();
                char hanzi = teacherName.charAt(0);
                if (!isChineseChar(hanzi)) {
                    continue;
                }
                //首字母
                String surname = convertSingleHanzi2Pinyin(teacherName);
                TeacherForSeacher teacherForSeacher = new TeacherForSeacher();
                teacherForSeacher.setTitle(surname);
                //具体信息
                ItemForteacher itemForteacher = new ItemForteacher();
                itemForteacher.setName(teacher.getName());
                itemForteacher.setKey(surname);
                itemForteacher.setTid(teacher.getTid());
                //封装具体信息，可以封装为list ，一共3个字段
                List<ItemForteacher> itemForteachersList = new ArrayList<>();
                itemForteachersList.add(itemForteacher);
                //封装成最终对象
                teacherForSeacher.setItem(itemForteachersList);
                //分组
                List<ItemForteacher> teacherForSeachers = teacherHash.get(surname);
                if (teacherHash.get(surname) == null) {
                    teacherForSeachers = new ArrayList<>();
                    teacherHash.put(surname, teacherForSeachers);
                }
                teacherForSeachers.add(itemForteacher);
            }
            //分好组后，封装返回结果
            for (String key : teacherHash.keySet()) {
                //这里表示拼音首字母相同的一类老师
                TeacherForSeacher teacher = new TeacherForSeacher();
                teacher.setTitle(key);
                List<ItemForteacher> items = teacherHash.get(key);
                teacher.setItem(items);
                teacherList.add(teacher);
            }
            lenForTeacher = teacherList.size();
        }
        return teacherList;
    }

    /**
     * 判断一个字符是否是汉字
     * PS：中文汉字的编码范围：[\u4e00-\u9fa5]
     *
     * @param c 需要判断的字符
     * @return 是汉字(true), 不是汉字(false)
     */
    public static boolean isChineseChar(char c) {
        return String.valueOf(c).matches("[\u4e00-\u9fa5]");
    }

    /*  *
     * @Description: 根据姓名生成姓氏首字母
     * @Author: king
     * @Date: 2020/4/12
     */
    private String convertSingleHanzi2Pinyin(String string) {
        char hanzi = string.charAt(0);
        if (!isChineseChar(hanzi)) {
            return String.valueOf(hanzi);
        }
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
