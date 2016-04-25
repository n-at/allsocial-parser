package ru.bserg.dao;

/**
 * Created by SBoichenko on 25.04.2016.
 */
public class Entity {

    /*

     "id": 244345,
            "vk_id": 48677005,
            "quantity": 15708,
            "caption": "Tutu.ru — туры и билеты по России и миру",
            "avatar": "http://cs622529.vk.me/v622529265/20c9b/kOJV1KwDB5M.jpg",
            "diff_abs": 13,
            "diff_rel": 0.082828926409685,
            "visitors": -1,
            "reach": -1,
            "cpp": -1,
            "in_search": 0,
            "category":             {
               "public":                [
                  "107",
                  "27",
                  "50"
               ],
               "private": []
            },
            "can_change_cpp": -1,
            "is_closed": 0,
            "is_verified": 0,
            "type_id": 1
     */

    Integer id;

    Integer vk_id;

    Long quantity;

    String caption;

    Long visitors;

    Long reach;

    Long cpp;

    Integer is_closed;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVk_id() {
        return vk_id;
    }

    public void setVk_id(Integer vk_id) {
        this.vk_id = vk_id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Long getVisitors() {
        return visitors;
    }

    public void setVisitors(Long visitors) {
        this.visitors = visitors;
    }

    public Long getReach() {
        return reach;
    }

    public void setReach(Long reach) {
        this.reach = reach;
    }

    public Long getCpp() {
        return cpp;
    }

    public void setCpp(Long cpp) {
        this.cpp = cpp;
    }

    public Integer getIs_closed() {
        return is_closed;
    }

    public void setIs_closed(Integer is_closed) {
        this.is_closed = is_closed;
    }
}
