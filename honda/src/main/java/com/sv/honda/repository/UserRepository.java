package com.sv.honda.repository;

import com.sv.honda.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends BaseRepository<UserEntity,Integer> {
    /**
     * 根据用户名查询符合条件的用户(模糊查询)
     * @param userName
     * @return 符合条件的用户列表
     */
    @Query("from UserEntity as u where u.userName like %:userName%")
    List<UserEntity> findUsersByName(@Param("userName") String userName);

    /**
     * 根据用户名查id符合条件的用户
     * @param userId
     * @return 符合条件的用户
     */
    @Query("from UserEntity as u where u.userId=:userId")
    UserEntity findUserById(@Param("userId") Integer userId);

    /**
     * 查询用户表总记录条数
     * @return
     */
    @Query("select count(*) from UserEntity")
    Integer findUserTotal();

    /**
     * 分页查询用户表记录
     * @param offset
     * @param pageSize
     * @return
     */
    @Query(value="select user_id,pass_word,user_name,user_state,address,DATE_FORMAT(revise_date, '%Y-%m-%dT%TZ') revise_date from user as u LIMIT :offset,:pageSize",nativeQuery=true)
    List<UserEntity> findAllUsersByPage(@Param("offset") Integer offset,@Param("pageSize") Integer pageSize);
}
