package org.patentminer.dao.impl;

import org.patentminer.bean.PageableImpl;
import org.patentminer.dao.UserDao;
import org.patentminer.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public Page<User> paginationList(Query query, int pageNo, int pageSize) {

        Pageable pageable = new PageableImpl(pageNo, pageSize);
        List<Sort.Order> orders = new ArrayList<Sort.Order>();  //排序
        orders.add(new Sort.Order(Sort.Direction.ASC, "id"));
        Sort sort = new Sort(orders);
        ((PageableImpl) pageable).setSort(sort);

        Long totalRecord = mongoTemplate.count(query, User.class);

        return new PageImpl<User>(mongoTemplate.find(query.with(pageable), User.class),
                pageable, totalRecord);
    }
}
