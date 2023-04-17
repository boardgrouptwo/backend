package com.khcare.spring.mapper;

import com.khcare.spring.dto.PaymentDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface PaymentMapper {
    @Select("SELECT pay_no, user_id, pay_amount, pay_date, pay_content FROM payment")
    PaymentDto paymentList();

    @Select("SELECT pay_no, user_id, pay_amount, pay_date, pay_content FROM payment WHERE user_id=#{user_id}")
    PaymentDto paymentUser(@Param("user_id") String user_id);

    @Insert("INSERT INTO pay")
    PaymentDto paymentInsert();

}
