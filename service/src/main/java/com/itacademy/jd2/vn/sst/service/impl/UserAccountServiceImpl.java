package com.itacademy.jd2.vn.sst.service.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.itacademy.jd2.vn.sst.dao.api.IUserAccountDao;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.vn.sst.dao.api.filter.UserAccountFilter;
import com.itacademy.jd2.vn.sst.service.IUserAccountService;
import com.itacademy.jd2.vn.sst.service.util.SendMailTLS;

@Service
public class UserAccountServiceImpl implements IUserAccountService {

	private IUserAccountDao dao;

	public UserAccountServiceImpl(IUserAccountDao dao) {
		super();
		this.dao = dao;

	}

	@Override
	public IUserAccount createEntity() {
		return dao.createEntity();
	}

	@Override
	public IUserAccount get(Integer id) {
		final IUserAccount entity = dao.get(id);
		return entity;
	}

	@Override
	public List<IUserAccount> getAll() {
		final List<IUserAccount> all = dao.selectAll();
		return all;
	}

	@Override
	public void save(IUserAccount entity) {
	
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		
		if (entity.getId() == null) {
			
			entity.setCreated(modifedOn);
		
			String password= entity.getPassword();
			String hash= null;
			try {
				// Create MessageDigest instance for MD5
				MessageDigest md = MessageDigest.getInstance("MD5");
				// Add password bytes to digest
				md.update(password.getBytes());
				// Get the hash's bytes
				byte[] bytes = md.digest();
				// This bytes[] has bytes in decimal format;
				// Convert it to hexadecimal format
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < bytes.length; i++) {
					sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
				}
				// Get complete hashed password in hex format
				hash = sb.toString();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			String email= entity.getEmail();
			SendMailTLS.main(email);
		entity.setPassword(hash);

			dao.insert(entity);
		} else {
			dao.update(entity);
		}
	}

	@Override
	public void delete(Integer id) {
		final IUserAccount iUserAccount = dao.get(id);
		iUserAccount.getClubs().clear();
		dao.update(iUserAccount);
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		dao.deleteAll();
	}

	@Override
	public IUserAccount getFullInfo(Integer id) {
		final IUserAccount entity = dao.getFullInfo(id);
		return entity;
	}

	@Override
	public long getCount(UserAccountFilter filter) {
		return dao.getCount(filter);
	}

	@Override
	public List<IUserAccount> find(UserAccountFilter filter) {
		return dao.find(filter);
	}

	@Override
	public IUserAccount getByEmail(String username) {
		return dao.getByEmail(username);
	}

}
