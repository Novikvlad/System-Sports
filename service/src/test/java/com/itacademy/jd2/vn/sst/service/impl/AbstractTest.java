package com.itacademy.jd2.vn.sst.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.itacademy.jd2.vn.sst.dao.api.entity.enums.PresenceType;
import com.itacademy.jd2.vn.sst.dao.api.entity.enums.UserType;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.ICity;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IClub;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.ICountry;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IEvent;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IFunOrganisation;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IPartner;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IPartnerContract;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IRegion;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IStadium;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.ITicket;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.vn.sst.dao.api.entity.table.ISeasonTicket;
import com.itacademy.jd2.vn.sst.service.ICityService;
import com.itacademy.jd2.vn.sst.service.IClubService;
import com.itacademy.jd2.vn.sst.service.ICountryService;
import com.itacademy.jd2.vn.sst.service.IEventServise;
import com.itacademy.jd2.vn.sst.service.IFunOrganisationService;
import com.itacademy.jd2.vn.sst.service.IPartnerContractService;
import com.itacademy.jd2.vn.sst.service.IPartnerService;
import com.itacademy.jd2.vn.sst.service.IRegionService;
import com.itacademy.jd2.vn.sst.service.IStadiumService;
import com.itacademy.jd2.vn.sst.service.ITicketService;
import com.itacademy.jd2.vn.sst.service.IUserAccountService;
import com.itacademy.jd2.vn.sst.service.ISeasonTicketService;

@SpringJUnitConfig(locations = "classpath:service-context-test.xml")
public abstract class AbstractTest {
	@Autowired
	protected IRegionService regionService;
	@Autowired
	protected IPartnerService partnerService;
	@Autowired
	protected IPartnerContractService partnerContractService;
	@Autowired
	protected ICountryService countryService;
	@Autowired
	protected ICityService cityService;
	@Autowired
	protected IStadiumService stadiumService;
	@Autowired
	protected IEventServise eventServise;
	@Autowired
	protected ITicketService ticketService;
	@Autowired
	protected IClubService clubServise;
	@Autowired
	protected IFunOrganisationService funOrganisationService;
	@Autowired
	protected ISeasonTicketService seasonTicketService;
	@Autowired
	protected IUserAccountService userAccountService;

	private static final Random RANDOM = new Random();

	@BeforeEach
	public void setUpMethod() {
		// clean DB recursive
		funOrganisationService.deleteAll();
		clubServise.deleteAll();
		seasonTicketService.deleteAll();
		eventServise.deleteAll();
		partnerContractService.deleteAll();
		stadiumService.deleteAll();
		cityService.deleteAll();
		countryService.deleteAll();
		regionService.deleteAll();
		partnerService.deleteAll();
	}

	protected String getRandomPrefix() {
		return RANDOM.nextInt(99999) + "";
	}

	protected int getRandomObjectsCount() {
		return RANDOM.nextInt(9) + 1;
	}

	protected Random getRandomDate() {
		return RANDOM;
	}

	protected BigDecimal getRandomDecimal() {
		BigDecimal randFromDouble = new BigDecimal(Math.random());
		return randFromDouble;
	}

	protected boolean getRandomBoolean() {
		return Math.random() < 0.5;
	}

	protected Random getRandom() {
		return RANDOM;
	}

	protected PresenceType getRandomPresenceType() {
		int presence = new Random().nextInt(PresenceType.values().length);
		return PresenceType.values()[presence];
	}

	protected UserType getRandomUserType() {
		int userType = new Random().nextInt(UserType.values().length);
		return UserType.values()[userType];
	}

	protected IRegion saveNewRegion() {
		final IRegion entity = regionService.createEntity();
		entity.setName("region-" + getRandomPrefix());
		regionService.save(entity);
		return entity;
	}

	protected IPartner saveNewPartner() {
		final IPartner entity = partnerService.createEntity();
		entity.setName("partner-" + getRandomPrefix());
		partnerService.save(entity);
		return entity;
	}

	protected ISeasonTicket saveNewSeasonTicket() {
		final ISeasonTicket entity = seasonTicketService.createEntity();
		entity.setName("seasonTicket-" + getRandomPrefix());
		entity.setSector("sector-" + getRandomPrefix());
		entity.setRow("row-" + getRandomPrefix());
		entity.setSeat("seat-" + getRandomPrefix());
		entity.setPrice(getRandomDecimal());
		entity.setDate(new Date());
		seasonTicketService.save(entity);
		return entity;
	}

	protected ICountry saveNewCountry() {
		final ICountry entity = countryService.createEntity();
		entity.setName("country-" + getRandomPrefix());
		entity.setRegion(saveNewRegion());
		countryService.save(entity);
		return entity;
	}

	protected ICity saveNewCity() {
		final ICity entity = cityService.createEntity();
		entity.setName("city-" + getRandomPrefix());
		entity.setCountry(saveNewCountry());
		cityService.save(entity);
		return entity;
	}

	protected IStadium saveNewStadium() {
		final IStadium entity = stadiumService.createEntity();
		entity.setName("stadium-" + getRandomPrefix());
		entity.setCapacity(getRandom().nextInt(100000));
		entity.setAddress("address-" + getRandomPrefix());
		entity.setBased(new Date());
		entity.setCity(saveNewCity());
		stadiumService.save(entity);
		return entity;

	}

	protected IEvent saveNewEvent() {
		final IEvent entity = eventServise.createEntity();
		entity.setName("event-" + getRandomPrefix());
		entity.setDate(new Date());
		entity.setStadium(saveNewStadium());
		eventServise.save(entity);
		return entity;

	}

	protected IClub saveNewClub() {
		final IClub entity = clubServise.createEntity();
		entity.setName("club-" + getRandomPrefix());
		entity.setBased(new Date());
		entity.setStadium(saveNewStadium());
		clubServise.save(entity);
		return entity;
	}

	protected IPartnerContract saveNewPartnerContract() {
		final IPartnerContract entity = partnerContractService.createEntity();
		entity.setClub(saveNewClub());
		entity.setPartner(saveNewPartner());
		entity.setDateSigning(new Date());
		entity.setContractTerm(new Date());
		entity.setContractValue(getRandomDecimal());
		partnerContractService.save(entity);
		return entity;
	}

	protected IFunOrganisation saveNewFunOrganisation() {
		final IFunOrganisation entity = funOrganisationService.createEntity();
		entity.setName("funOrganisation-" + getRandomPrefix());
		entity.setClub(saveNewClub());
		entity.setCity(saveNewCity());
		entity.setDeposit(getRandomDecimal());
		funOrganisationService.save(entity);
		return entity;
	}

	protected ITicket saveNewTicket() {
		final ITicket entity = ticketService.createEntity();
		entity.setName("ticket-" + getRandomPrefix());
		entity.setSector("sector-" + getRandomPrefix());
		entity.setRow("row-" + getRandomPrefix());
		entity.setSeat("seat-" + getRandomPrefix());
		entity.setPrice(getRandomDecimal());
		entity.setPresence(getRandomPresenceType());
		entity.setEvent(saveNewEvent());
		ticketService.save(entity);
		return entity;
	}

	protected IUserAccount saveNewUserAccount() {
		final IUserAccount entity = userAccountService.createEntity();
		entity.setName("ticket-" + getRandomPrefix());
		entity.setEmail("email-" + getRandomPrefix());
		entity.setPassword("password-" + getRandomPrefix());
		entity.setFunOrganisation(saveNewFunOrganisation());
		entity.setCountry(saveNewCountry());
		entity.setPhone("phone-" +getRandomPrefix());
		entity.setBirthday(new Date());
		entity.setRole(getRandomUserType());
		
		userAccountService.save(entity);
		return entity;
	}
}