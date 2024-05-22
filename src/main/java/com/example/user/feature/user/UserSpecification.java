package com.example.user.feature.user;

import com.example.user.domain.Generation;
import com.example.user.domain.User;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {
    public static Specification<User> hasGenType(String genType) {
        return (root, query, cb) -> {
            if (genType != null) {
                Join<User, Generation> genInfoJoin = root.join("al_generations");
                return cb.equal(genInfoJoin.get("gen-type"), genType);
            } else {
                return null;
            }
        };
    }

    public static Specification<User> hasGenNum(String genNum) {
        return (root, query, cb) -> {
            if (genNum != null) {
                Join<User, Generation> genInfoJoin = root.join("al_generations");
                return cb.equal(genInfoJoin.get("gen_num"), genNum);
            } else {
                return null;
            }
        };
    }
}
