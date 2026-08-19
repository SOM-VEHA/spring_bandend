//package com.spring_bandend.spring_bandend.specification;
//import org.springframework.data.jpa.domain.Specification;
//import com.somveha.spring_bandend.entity.Role;
//import com.somveha.spring_bandend.feature.dto.filter.RoleFilter;
//import com.somveha.spring_bandend.util.PageUtil;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.util.StringUtils;
//
//import java.util.List;
//// public class RoleSpecification {
////     public static Specification<Role> hasNameContaining(String name) {
////         return (root, query, criteriaBuilder) -> {
////             if (name.isBlank()) {
////                 return null;
////             }
////             return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
////         };
////     }
//
////     public static Specification<Role> builderSpecification(Map<String, String> params) {
////         return (root, query, criteriaBuilder) -> {
////             Predicate predicate = criteriaBuilder.conjunction();
////             if (params.containsKey("name") && params.get("name") != null && !params.get("name").isEmpty()) {
////                 predicate = criteriaBuilder.and(predicate,hasNameContaining(params.get("name")).toPredicate(root, query, criteriaBuilder));
////             }
////             return predicate;
////         };
////     }
//// }
//
//
//public final class RoleSpecificationOld {
//
//    private RoleSpecificationOld() {
//    }
//
//    // 3 fiels
//    // 2 field
//    private static final String FIELD_ID = "id";
//    private static final String FIELD_NAME = "name";
//    private static final List<String> ALLOWED_SORT_FIELDS = List.of(FIELD_NAME, FIELD_ID);
//
//    private static Specification<Role> hasName(String name) {
//        return (root, query, cb) -> {
//            if (!StringUtils.hasText(name)) {
//                return null;
//            }
//
//            return cb.like(
//                    cb.lower(root.get(FIELD_NAME)),
//                    "%" + name.trim().toLowerCase() + "%"
//            );
//        };
//    }
//
//
//    /** WHERE clause: case-insensitive contains on name and/or code. */
//    public static Specification<Role> build(RoleFilter filter) {
//        if (filter == null) {
//            return Specification.allOf(
//                    hasName(null)
//            );
//        }
//
//        return Specification.allOf(
//                hasName(filter.getName())
//        );
//    }
//
//    public static Sort sort(RoleFilter filter) {
//        return PageUtil.sort(filter, FIELD_NAME, ALLOWED_SORT_FIELDS);
//    }
//
//    public static Pageable pageable(RoleFilter filter) {
//        return PageUtil.pageable(filter, FIELD_NAME, ALLOWED_SORT_FIELDS);
//    }
//}