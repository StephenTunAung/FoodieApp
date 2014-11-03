package com.keyit.dto;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "tbl_promotion")
public class Promotion implements Serializable {

	private static final long serialVersionUID = 8580201043897238240L;

	@Id
	@Column(name = "PromotionID")
	@GeneratedValue
	private Integer promotionId;

	@OneToOne
	@JoinColumn(name = "RestaurantID", referencedColumnName = "RestaurantID")
	private Restaurant restaurant;

	@Column(name = "PromoName")
	private String promoName;

	@Column(name = "PromoDescription")
	private String promoDescription;

	@Column(name = "ValidDate")
	private String validDate;

	@Column(name = "ValidOnMonday")
	private boolean validOnMonday;

	@Column(name = "ValidOnTuesday")
	private boolean validOnTuesday;

	@Column(name = "ValidOnWednesday")
	private boolean validOnWednesday;

	@Column(name = "ValidOnThursday")
	private boolean validOnThursday;

	@Column(name = "ValidOnFriday")
	private boolean validOnFriday;

	@Column(name = "ValidOnSatursday")
	private boolean validOnSatursday;

	@Column(name = "ValidOnSunday")
	private boolean validOnSunday;

	@Column(name = "ValidOnPH")
	private boolean validOnPH;

	@Column(name = "ValidNA")
	private boolean validNA;

	@Column(name = "PromoImage")
	@Lob
	private Blob promoImage;

	@Transient
	private MultipartFile promoImagePart;

	@Column(name = "PromoThumb")
	@Lob
	private Blob promoThumb;

	@Transient
	private MultipartFile promoThumbPart;

	public Integer getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(Integer promotionId) {
		this.promotionId = promotionId;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public String getPromoName() {
		return promoName;
	}

	public void setPromoName(String promoName) {
		this.promoName = promoName;
	}

	public String getPromoDescription() {
		return promoDescription;
	}

	public void setPromoDescription(String promoDescription) {
		this.promoDescription = promoDescription;
	}

	public String getValidDate() {
		return validDate;
	}

	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}

	public boolean isValidOnMonday() {
		return validOnMonday;
	}

	public void setValidOnMonday(boolean validOnMonday) {
		this.validOnMonday = validOnMonday;
	}

	public boolean isValidOnTuesday() {
		return validOnTuesday;
	}

	public void setValidOnTuesday(boolean validOnTuesday) {
		this.validOnTuesday = validOnTuesday;
	}

	public boolean isValidOnWednesday() {
		return validOnWednesday;
	}

	public void setValidOnWednesday(boolean validOnWednesday) {
		this.validOnWednesday = validOnWednesday;
	}

	public boolean isValidOnThursday() {
		return validOnThursday;
	}

	public void setValidOnThursday(boolean validOnThursday) {
		this.validOnThursday = validOnThursday;
	}

	public boolean isValidOnFriday() {
		return validOnFriday;
	}

	public void setValidOnFriday(boolean validOnFriday) {
		this.validOnFriday = validOnFriday;
	}

	public boolean isValidOnSatursday() {
		return validOnSatursday;
	}

	public void setValidOnSatursday(boolean validOnSatursday) {
		this.validOnSatursday = validOnSatursday;
	}

	public boolean isValidOnSunday() {
		return validOnSunday;
	}

	public void setValidOnSunday(boolean validOnSunday) {
		this.validOnSunday = validOnSunday;
	}

	public boolean isValidOnPH() {
		return validOnPH;
	}

	public void setValidOnPH(boolean validOnPH) {
		this.validOnPH = validOnPH;
	}

	public boolean isValidNA() {
		return validNA;
	}

	public void setValidNA(boolean validNA) {
		this.validNA = validNA;
	}

	public Blob getPromoImage() {
		return promoImage;
	}

	public void setPromoImage(Blob promoImage) {
		this.promoImage = promoImage;
	}

	public MultipartFile getPromoImagePart() {
		return promoImagePart;
	}

	public void setPromoImagePart(MultipartFile promoImagePart) {
		this.promoImagePart = promoImagePart;
	}

	public Blob getPromoThumb() {
		return promoThumb;
	}

	public void setPromoThumb(Blob promoThumb) {
		this.promoThumb = promoThumb;
	}

	public MultipartFile getPromoThumbPart() {
		return promoThumbPart;
	}

	public void setPromoThumbPart(MultipartFile promoThumbPart) {
		this.promoThumbPart = promoThumbPart;
	}

}
