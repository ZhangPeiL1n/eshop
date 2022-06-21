package com.zpl.eshop.auth.controller;

import com.zpl.eshop.auth.domain.AccountDTO;
import com.zpl.eshop.auth.domain.AccountQuery;
import com.zpl.eshop.auth.domain.AccountVO;
import com.zpl.eshop.auth.service.AccountService;
import com.zpl.eshop.common.util.CloneDirection;
import com.zpl.eshop.common.util.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangPeiL1n
 * @date 2022/6/1 23:39
 **/
@RestController
@RequestMapping("/auth/account ")
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    /**
     * 帐号管理 service 组件
     */
    @Autowired
    private AccountService accountService;

    /**
     * 分页查询帐号
     *
     * @param query 查询条件
     * @return 帐号
     */
    @GetMapping("/")
    public List<AccountVO> listByPage(AccountQuery query) {
        try {
            List<AccountDTO> accounts = accountService.listByPage(query);
            List<AccountVO> resultAccounts = ObjectUtils.convertList(accounts, AccountVO.class, CloneDirection.OPPOSITE);
            return resultAccounts;
        } catch (Exception e) {
            logger.error("error", e);
            return new ArrayList<>();
        }
    }

    /**
     * 根据id查询角色
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public AccountVO getById(@PathVariable("id") Long id) {
        try {
            AccountDTO account = accountService.getById(id);
            AccountVO resultAccount = account.clone(AccountVO.class, CloneDirection.OPPOSITE);
            return resultAccount;
        } catch (Exception e) {
            logger.error("error", e);
            return new AccountVO();
        }
    }

    /**
     * 新增帐号
     *
     * @param account 帐号
     * @return 执行结果
     */
    @PostMapping("/")
    public Boolean save(@RequestBody AccountVO account) {
        try {
            AccountDTO targetAccount = account.clone(AccountDTO.class, CloneDirection.FORWARD);
            accountService.save(targetAccount);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 更新帐号
     *
     * @param account 帐号对象
     * @return 操作结果
     */
    @PutMapping("/{id}")
    public Boolean update(@RequestBody AccountVO account) {
        try {
            AccountDTO targetAccount = account.clone(AccountDTO.class);
            accountService.update(targetAccount);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 根据帐号id删除
     *
     * @param id 帐号id
     * @return 操作结果
     */
    @DeleteMapping("/{id}")
    public Boolean remove(@PathVariable("id") Long id) {
        try {
            accountService.remove(id);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 更新密码
     *
     * @param id       帐号id
     * @param password 密码
     * @return 操作结果
     */
    @PutMapping("/password/{id}")
    public Boolean updatePassword(@PathVariable("id") Long id, String password) {
        try {
            AccountDTO account = accountService.getById(id);
            account.setPassword(password);
            accountService.update(account);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }
}
