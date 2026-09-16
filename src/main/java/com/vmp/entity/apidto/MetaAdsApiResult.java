package com.vmp.entity.apidto;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class MetaAdsApiResult<T> {

    private List<T> data;

    private Page paging;

    private ErrorInfo error;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Page getPaging() {
        return paging;
    }

    public void setPaging(Page paging) {
        this.paging = paging;
    }

    public ErrorInfo getError() {
        return error;
    }

    public void setError(ErrorInfo error) {
        this.error = error;
    }

    public static class Page {
        private Cursor cursors;
        private String next;

        public Cursor getCursors() {
            return cursors;
        }

        public void setCursors(Cursor cursors) {
            this.cursors = cursors;
        }

        public String getNext() {
            return next;
        }

        public void setNext(String next) {
            this.next = next;
        }
    }

    public static class Cursor {
        private String before;
        private String after;

        public String getBefore() {
            return before;
        }

        public void setBefore(String before) {
            this.before = before;
        }

        public String getAfter() {
            return after;
        }

        public void setAfter(String after) {
            this.after = after;
        }
    }

    public static class ErrorInfo {
        private String message;
        private String type;
        private Integer code;
        @JSONField(name = "error_subcode")
        private Integer errorSubcode;
        @JSONField(name = "is_transient")
        private Boolean transientError;
        @JSONField(name = "error_user_title")
        private String errorUserTitle;
        @JSONField(name = "error_user_msg")
        private String errorUserMessage;
        @JSONField(name = "fbtrace_id")
        private String traceId;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public Integer getErrorSubcode() {
            return errorSubcode;
        }

        public void setErrorSubcode(Integer errorSubcode) {
            this.errorSubcode = errorSubcode;
        }

        public Boolean getTransientError() {
            return transientError;
        }

        public void setTransientError(Boolean transientError) {
            this.transientError = transientError;
        }

        public String getErrorUserTitle() {
            return errorUserTitle;
        }

        public void setErrorUserTitle(String errorUserTitle) {
            this.errorUserTitle = errorUserTitle;
        }

        public String getErrorUserMessage() {
            return errorUserMessage;
        }

        public void setErrorUserMessage(String errorUserMessage) {
            this.errorUserMessage = errorUserMessage;
        }

        public String getTraceId() {
            return traceId;
        }

        public void setTraceId(String traceId) {
            this.traceId = traceId;
        }
    }

}
