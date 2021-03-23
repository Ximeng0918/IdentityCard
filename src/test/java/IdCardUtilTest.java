import club.ximeng.util.IdCardUtil;
import org.junit.Test;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * 身份证单元测试
 *
 * @author liu xm
 * @date 2021/3/23 13:19
 */
public class IdCardUtilTest {
    private static final String ID_18 = "321083197812162119";
    private static final String ID_15 = "372901880730303";

    @Test
    public void isValidCardTest() {
        boolean valid = IdCardUtil.isValidCard(ID_18);
        System.out.println(valid);

        boolean valid15 = IdCardUtil.isValidCard(ID_15);
        System.out.println(valid15);

        // 无效
        String idCard = "360198910283844";
        System.out.println(IdCardUtil.isValidCard(idCard));

        // 生日无效
        idCard = "201511221897205960";
        System.out.println(IdCardUtil.isValidCard(idCard));

        // 生日无效
        idCard = "815727834224151";
        System.out.println(IdCardUtil.isValidCard(idCard));
    }

    @Test
    public void convert15To18Test() {
        String convert15To18 = IdCardUtil.convert15To18(ID_15);
        System.out.println(convert15To18);

        String convert15To18Second = IdCardUtil.convert15To18("330102200403064");
        System.out.println(convert15To18Second);

        IdCardUtil.convert15To18(null);
    }

    @Test
    public void getAgeByIdCardTest() {
        long date = LocalDate.parse("2017-04-10", DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                .atStartOfDay().toInstant(ZoneOffset.of("+8")).toEpochMilli();

        int age = IdCardUtil.getAgeByIdCard(ID_18, date);
        System.out.println(age);

        int age2 = IdCardUtil.getAgeByIdCard(ID_15, date);
        System.out.println(age2);
    }

    @Test
    public void getBirthByIdCardTest() {
        String birth = IdCardUtil.getBirthByIdCard(ID_18);
        System.out.println(birth);

        String birth2 = IdCardUtil.getBirthByIdCard(ID_15);
        System.out.println(birth2);
    }

    @Test
    public void getProvinceByIdCardTest() {
        String province = IdCardUtil.getProvinceByIdCard(ID_18);
        System.out.println(province);

        String province2 = IdCardUtil.getProvinceByIdCard(ID_15);
        System.out.println(province2);
    }

    @Test
    public void getCityCodeByIdCardTest() {
        String codeByIdCard = IdCardUtil.getCityCodeByIdCard(ID_18);
        System.out.println(codeByIdCard);
    }

    @Test
    public void getGenderByIdCardTest() {
        int gender = IdCardUtil.getGenderByIdCard(ID_18);
        System.out.println(gender);
    }

    @Test
    public void isValidCard18Test() {
        boolean isValidCard18 = IdCardUtil.isValidCard18("3301022011022000D6");
        System.out.println(isValidCard18);

        // 不忽略大小写情况下，X严格校验必须大写
        isValidCard18 = IdCardUtil.isValidCard18("33010219200403064x", false);
        System.out.println(isValidCard18);
        isValidCard18 = IdCardUtil.isValidCard18("33010219200403064X", false);
        System.out.println(isValidCard18);

        // 非严格校验下大小写皆可
        isValidCard18 = IdCardUtil.isValidCard18("33010219200403064x");
        System.out.println(isValidCard18);
        isValidCard18 = IdCardUtil.isValidCard18("33010219200403064X");
        System.out.println(isValidCard18);
    }

    @Test
    public void isValidHKCardIdTest() {
        String hkCard = "P174468(6)";
        boolean flag = IdCardUtil.isValidHongKongCard(hkCard);
        System.out.println(flag);
    }
}
