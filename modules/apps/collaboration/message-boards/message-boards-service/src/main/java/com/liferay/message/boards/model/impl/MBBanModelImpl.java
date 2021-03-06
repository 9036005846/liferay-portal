/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.message.boards.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.message.boards.model.MBBan;
import com.liferay.message.boards.model.MBBanModel;
import com.liferay.message.boards.model.MBBanSoap;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the MBBan service. Represents a row in the &quot;MBBan&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link MBBanModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link MBBanImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MBBanImpl
 * @see MBBan
 * @see MBBanModel
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class MBBanModelImpl extends BaseModelImpl<MBBan> implements MBBanModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a message boards ban model instance should use the {@link MBBan} interface instead.
	 */
	public static final String TABLE_NAME = "MBBan";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "banId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "banUserId", Types.BIGINT },
			{ "lastPublishDate", Types.TIMESTAMP }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("banId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("banUserId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("lastPublishDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE = "create table MBBan (uuid_ VARCHAR(75) null,banId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,banUserId LONG,lastPublishDate DATE null)";
	public static final String TABLE_SQL_DROP = "drop table MBBan";
	public static final String ORDER_BY_JPQL = " ORDER BY mbBan.banId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY MBBan.banId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.message.boards.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.message.boards.model.MBBan"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.message.boards.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.message.boards.model.MBBan"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.message.boards.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.message.boards.model.MBBan"),
			true);
	public static final long BANUSERID_COLUMN_BITMASK = 1L;
	public static final long COMPANYID_COLUMN_BITMASK = 2L;
	public static final long GROUPID_COLUMN_BITMASK = 4L;
	public static final long USERID_COLUMN_BITMASK = 8L;
	public static final long UUID_COLUMN_BITMASK = 16L;
	public static final long BANID_COLUMN_BITMASK = 32L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static MBBan toModel(MBBanSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		MBBan model = new MBBanImpl();

		model.setUuid(soapModel.getUuid());
		model.setBanId(soapModel.getBanId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setBanUserId(soapModel.getBanUserId());
		model.setLastPublishDate(soapModel.getLastPublishDate());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<MBBan> toModels(MBBanSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<MBBan> models = new ArrayList<MBBan>(soapModels.length);

		for (MBBanSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.message.boards.service.util.ServiceProps.get(
				"lock.expiration.time.com.liferay.message.boards.model.MBBan"));

	public MBBanModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _banId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setBanId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _banId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return MBBan.class;
	}

	@Override
	public String getModelClassName() {
		return MBBan.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("banId", getBanId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("banUserId", getBanUserId());
		attributes.put("lastPublishDate", getLastPublishDate());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long banId = (Long)attributes.get("banId");

		if (banId != null) {
			setBanId(banId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long banUserId = (Long)attributes.get("banUserId");

		if (banUserId != null) {
			setBanUserId(banUserId);
		}

		Date lastPublishDate = (Date)attributes.get("lastPublishDate");

		if (lastPublishDate != null) {
			setLastPublishDate(lastPublishDate);
		}
	}

	@JSON
	@Override
	public String getUuid() {
		if (_uuid == null) {
			return StringPool.BLANK;
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	@JSON
	@Override
	public long getBanId() {
		return _banId;
	}

	@Override
	public void setBanId(long banId) {
		_banId = banId;
	}

	@JSON
	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_columnBitmask |= GROUPID_COLUMN_BITMASK;

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = _groupId;
		}

		_groupId = groupId;
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_columnBitmask |= USERID_COLUMN_BITMASK;

		if (!_setOriginalUserId) {
			_setOriginalUserId = true;

			_originalUserId = _userId;
		}

		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return StringPool.BLANK;
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	public long getOriginalUserId() {
		return _originalUserId;
	}

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return StringPool.BLANK;
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@JSON
	@Override
	public long getBanUserId() {
		return _banUserId;
	}

	@Override
	public void setBanUserId(long banUserId) {
		_columnBitmask |= BANUSERID_COLUMN_BITMASK;

		if (!_setOriginalBanUserId) {
			_setOriginalBanUserId = true;

			_originalBanUserId = _banUserId;
		}

		_banUserId = banUserId;
	}

	@Override
	public String getBanUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getBanUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return StringPool.BLANK;
		}
	}

	@Override
	public void setBanUserUuid(String banUserUuid) {
	}

	public long getOriginalBanUserId() {
		return _originalBanUserId;
	}

	@JSON
	@Override
	public Date getLastPublishDate() {
		return _lastPublishDate;
	}

	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		_lastPublishDate = lastPublishDate;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(PortalUtil.getClassNameId(
				MBBan.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			MBBan.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public MBBan toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (MBBan)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		MBBanImpl mbBanImpl = new MBBanImpl();

		mbBanImpl.setUuid(getUuid());
		mbBanImpl.setBanId(getBanId());
		mbBanImpl.setGroupId(getGroupId());
		mbBanImpl.setCompanyId(getCompanyId());
		mbBanImpl.setUserId(getUserId());
		mbBanImpl.setUserName(getUserName());
		mbBanImpl.setCreateDate(getCreateDate());
		mbBanImpl.setModifiedDate(getModifiedDate());
		mbBanImpl.setBanUserId(getBanUserId());
		mbBanImpl.setLastPublishDate(getLastPublishDate());

		mbBanImpl.resetOriginalValues();

		return mbBanImpl;
	}

	@Override
	public int compareTo(MBBan mbBan) {
		long primaryKey = mbBan.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MBBan)) {
			return false;
		}

		MBBan mbBan = (MBBan)obj;

		long primaryKey = mbBan.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		MBBanModelImpl mbBanModelImpl = this;

		mbBanModelImpl._originalUuid = mbBanModelImpl._uuid;

		mbBanModelImpl._originalGroupId = mbBanModelImpl._groupId;

		mbBanModelImpl._setOriginalGroupId = false;

		mbBanModelImpl._originalCompanyId = mbBanModelImpl._companyId;

		mbBanModelImpl._setOriginalCompanyId = false;

		mbBanModelImpl._originalUserId = mbBanModelImpl._userId;

		mbBanModelImpl._setOriginalUserId = false;

		mbBanModelImpl._setModifiedDate = false;

		mbBanModelImpl._originalBanUserId = mbBanModelImpl._banUserId;

		mbBanModelImpl._setOriginalBanUserId = false;

		mbBanModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<MBBan> toCacheModel() {
		MBBanCacheModel mbBanCacheModel = new MBBanCacheModel();

		mbBanCacheModel.uuid = getUuid();

		String uuid = mbBanCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			mbBanCacheModel.uuid = null;
		}

		mbBanCacheModel.banId = getBanId();

		mbBanCacheModel.groupId = getGroupId();

		mbBanCacheModel.companyId = getCompanyId();

		mbBanCacheModel.userId = getUserId();

		mbBanCacheModel.userName = getUserName();

		String userName = mbBanCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			mbBanCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			mbBanCacheModel.createDate = createDate.getTime();
		}
		else {
			mbBanCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			mbBanCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			mbBanCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		mbBanCacheModel.banUserId = getBanUserId();

		Date lastPublishDate = getLastPublishDate();

		if (lastPublishDate != null) {
			mbBanCacheModel.lastPublishDate = lastPublishDate.getTime();
		}
		else {
			mbBanCacheModel.lastPublishDate = Long.MIN_VALUE;
		}

		return mbBanCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", banId=");
		sb.append(getBanId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", banUserId=");
		sb.append(getBanUserId());
		sb.append(", lastPublishDate=");
		sb.append(getLastPublishDate());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(34);

		sb.append("<model><model-name>");
		sb.append("com.liferay.message.boards.model.MBBan");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>banId</column-name><column-value><![CDATA[");
		sb.append(getBanId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>banUserId</column-name><column-value><![CDATA[");
		sb.append(getBanUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>lastPublishDate</column-name><column-value><![CDATA[");
		sb.append(getLastPublishDate());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = MBBan.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			MBBan.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _banId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private long _originalUserId;
	private boolean _setOriginalUserId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _banUserId;
	private long _originalBanUserId;
	private boolean _setOriginalBanUserId;
	private Date _lastPublishDate;
	private long _columnBitmask;
	private MBBan _escapedModel;
}