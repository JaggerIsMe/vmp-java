package com.vmp.entity.constants;

public class Constants {
    public static final String ZERO_STR = "0";

    public static final Integer ZERO = 0;

    public static final Integer ONE = 1;

    public static final Integer LENGTH_30 = 30;

    public static final Integer LENGTH_10 = 10;

    public static final Integer LENGTH_20 = 20;

    public static final Integer LENGTH_5 = 5;

    public static final Integer LENGTH_15 = 15;

    public static final Integer LENGTH_150 = 150;

    public static final Integer LENGTH_50 = 50;

    public static final String TOKEN_KEY = "token";

    public static final String SESSION_SHARE_KEY = "session_share_key_";

    public static final String FILE_FOLDER_FILE = "/files/";

    public static final String FILE_FOLDER_TEMP = "/temp/";

    public static final String IMAGE_PNG_SUFFIX = ".png";

    public static final String COVER_IMAGE_SUFFIX = "_cover.png";

    public static final String TS_NAME = "index.ts";

    public static final String M3U8_NAME = "index.m3u8";

    public static final String AVATAR_SUFFIX = ".jpg";

    public static final String AVATAR_COVER_SUFFIX = "_cover.jpg";

    public static final String FILE_FOLDER_AVATAR_NAME = "avatar/";

    public static final String AVATAR_DEFUALT = "default_avatar.jpg";

    public static final String VIEW_OBJ_RESULT_KEY = "result";

    public static final String WEB_TOKEN_KEY = "token";

    public static final String WEB_DDOPENUNIONID_KEY = "ddOpenUnionId";

    /**
     * redis key 相关
     */

    /**
     * 过期时间 1分钟
     */
    public static final Integer REDIS_KEY_EXPIRES_ONE_MIN = 60;

    /**
     * 过期时间 1天
     */
    public static final Integer REDIS_KEY_EXPIRES_DAY = REDIS_KEY_EXPIRES_ONE_MIN * 60 * 24;

    public static final Integer REDIS_KEY_EXPIRES_ONE_HOUR = REDIS_KEY_EXPIRES_ONE_MIN * 60;

    public static final Long MB = 1024 * 1024L;

    /**
     * 过期时间5分钟
     */
    public static final Integer REDIS_KEY_EXPIRES_FIVE_MIN = REDIS_KEY_EXPIRES_ONE_MIN * 5;

    /**
     * 过期时间10分钟
     */
    public static final Integer REDIS_KEY_EXPIRES_TEN_MIN = REDIS_KEY_EXPIRES_ONE_MIN * 10;

    public static final String REDIS_KEY_ONLINE_TOKEN = "vmp:online:token:";

    public static final String REDIS_KEY_ONLINE_USERID_LATEST_TOKEN = "vmp:online:userId:";

}
