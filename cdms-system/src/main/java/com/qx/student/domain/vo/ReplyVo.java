package com.qx.student.domain.vo;

import com.qx.common.annotation.Excel;
import com.qx.student.domain.CaseForum;
import org.apache.ibatis.annotations.Case;

/**
 * @author qq
 * @version 1.0
 * @date 2021/12/21 9:40
 */
public class ReplyVo {

    private Long forumId;

    private int forumCount;

    private CaseForum caseForum;

    public CaseForum getCaseForum() {
        return caseForum;
    }

    public void setCaseForum(CaseForum caseForum) {
        this.caseForum = caseForum;
    }

    public Long getForumId() {
        return forumId;
    }

    public void setForumId(Long forumId) {
        this.forumId = forumId;
    }

    public int getForumCount() {
        return forumCount;
    }

    public void setForumCount(int forumCount) {
        this.forumCount = forumCount;
    }
}
