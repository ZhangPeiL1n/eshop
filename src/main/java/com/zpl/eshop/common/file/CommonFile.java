package com.zpl.eshop.common.file;

/**
 * 文件操作接口
 *
 * @author ZhangPeiL1n
 * @date 2022/10/22 22:53
 **/
public interface CommonFile {

    /**
     * 判断文件是否存在
     *
     * @return 是否存在
     * @throws Exception
     */
    Boolean exists() throws Exception;

    /**
     * 递归创建所有子目录
     *
     * @return 处理结果
     * @throws Exception
     */
    Boolean createDirectories() throws Exception;

    /**
     * 获取文件的绝对路径
     *
     * @return 绝对路径
     * @throws Exception
     */
    String getAbsolutePath() throws Exception;

    /**
     * 获取文件名
     *
     * @return 文件名
     * @throws Exception
     */
    String getFilename() throws Exception;

    /**
     * 删除文件
     *
     * @return 处理结果
     * @throws Exception
     */
    Boolean remove() throws Exception;

    /**
     * 获取文件路径
     *
     * @return 文件路径
     * @throws Exception
     */
    String getPath() throws Exception;
}
