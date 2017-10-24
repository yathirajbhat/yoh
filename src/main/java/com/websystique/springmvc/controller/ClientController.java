package com.websystique.springmvc.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.websystique.springmvc.model.AppResponse;
import com.websystique.springmvc.model.CampaignContents;
import com.websystique.springmvc.model.CampaignResponse;
import com.websystique.springmvc.model.ContentPlayingNow;
import com.websystique.springmvc.model.ContentRequest;
import com.websystique.springmvc.model.Device;
import com.websystique.springmvc.model.DeviceCategory;
import com.websystique.springmvc.model.DeviceLocation;
import com.websystique.springmvc.model.FullSlots;
import com.websystique.springmvc.model.PriceRequest;
import com.websystique.springmvc.model.PriceResponse;
import com.websystique.springmvc.model.Response;
import com.websystique.springmvc.model.UserDocument;
import com.websystique.springmvc.service.ContentPlayingNowService;
import com.websystique.springmvc.service.DeviceCategoryService;
import com.websystique.springmvc.service.DeviceLocationService;
import com.websystique.springmvc.service.DeviceService;
import com.websystique.springmvc.service.PlayingPriceService;
import com.websystique.springmvc.service.UserDocumentService;
import com.websystique.springmvc.util.EncryptUtils;

@RestController
@RequestMapping("/client/")
public class ClientController {

	@Autowired
	ContentPlayingNowService contentPlayingNowService;
	
	@Autowired
	DeviceService deviceService;
	
	@Autowired
	DeviceLocationService deviceLocationService;
	
	@Autowired
	UserDocumentService userDocumentService;
	
	@Autowired
	PlayingPriceService playingPriceService;
	
	@Autowired
	DeviceCategoryService deviceCategoryService;
	
	@RequestMapping(value = "/ads", method = RequestMethod.POST)
	public AppResponse getFile(@RequestParam (name="id") String id){
		List<ContentPlayingNow> cont = contentPlayingNowService.findByDeviceId(Integer.parseInt(id));
		for (ContentPlayingNow contentPlayingNow : cont) {
		System.out.println(contentPlayingNow.getUserDocument().getPlayGroup());
		}
		
		AppResponse response = new AppResponse();
		
		ArrayList<CampaignResponse> campresponse = new ArrayList<CampaignResponse>(); 
				
		for(int i=0;i<cont.size();i++){
			
			CampaignResponse res= new CampaignResponse();
			res.setDelay(cont.get(i).getDelay());
			res.setStartDateTime(cont.get(i).getStartTime());
			res.setEndDateTime(cont.get(i).getEndTime());
			res.setUnit(cont.get(i).getDelayUnit());
			CampaignContents campaignContents = new CampaignContents();
			List<CampaignContents> campaignContents2 = new ArrayList<CampaignContents>();
			campaignContents.setImageDetails(cont.get(i).getUserDocument().getFileLocation());
			campaignContents.setUnqiqueIdentifier(cont.get(i).getUserDocument().getUniqueIdentifier());
			campaignContents2.add(campaignContents);
			res.setCampaignContents(campaignContents2);
			//if(cont.get(i).getUserDocument().getPlayGroup())
			campresponse.add(res);
		}
		
		 response.setResponse(campresponse);
		 return response;
	}
	
	@RequestMapping(value = "/devices",method = {RequestMethod.POST})
	public List<Device> getAllDevices(@RequestParam (name="deviceLocationId") String deviceLocationId){
		return deviceService.getDevicesByLocation(Integer.parseInt(deviceLocationId));
	}
	
	@RequestMapping(value = "/deviceByLocations",method =RequestMethod.POST)
	public List<Device> getDevicesByLocations(@RequestBody List<Integer> deviceLocationId){
		return deviceService.getDevicesByLocation(deviceLocationId);
	}
	
	@RequestMapping(value = "/deviceLocation",method = {RequestMethod.POST})
	public List<DeviceLocation> getAllDevicesByLocation(@RequestParam (name="cityName") String cityName){
		return deviceLocationService.getLocationByCity(cityName);
	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/saveContents",method = {RequestMethod.POST})
	public Response saveContent(@RequestBody ContentRequest contentRequest){
		Response response = new Response();
		String value=getDateAfter1Month(contentRequest.getEndDate());
		if(contentRequest.getStartDate()==null || contentRequest.getEndDate()==null || contentRequest.getDeviceId()==null){
			response.setResponseCode(400);
			response.setResponseMessage("Please select appropriate Dates and Devices.");
		}
		else if(contentRequest.getDeviceId().isEmpty()){
			response.setResponseCode(400);
			response.setResponseMessage("Please choose atleast 1 Device(s).");
		}
		else if (contentRequest.getStartDate().after(contentRequest.getEndDate())){
			response.setResponseCode(400);
			response.setResponseMessage("Please select End date after Start date");
		}
		else if(contentRequest.getPrice() <=0){
			response.setResponseCode(400);
			response.setResponseMessage("Please select appropriate Dates and Devices. \n Price cannot be '0'(Zero)");
		}
		else if(contentRequest.getStartDate().getDate() == (new Date().getDate()) && contentRequest.getStartDate().getMonth() == new Date().getMonth()){
			response.setResponseCode(400);
			response.setResponseMessage("Start Date cannot be Today's Date");
		}
		else if(!value.equalsIgnoreCase("true"))
		{
			response.setResponseCode(400);
			response.setResponseMessage("Please Schedule campaign before "+value);
		}
		else
		 response=saveContents(contentRequest, response);
		 return response;
	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/price",method=RequestMethod.POST)
	public PriceResponse getPrice(@RequestBody PriceRequest priceRequest){
		PriceResponse response = new PriceResponse();
		String value=getDateAfter1Month(priceRequest.getEndDate());
		if(priceRequest.getStartDate()==null || priceRequest.getEndDate()==null || priceRequest.getDeviceId()==null){
			response.setResponseCode(400);
			response.setResponseMessage("Please select appropriate Dates and Devices.");
		}
		else if(priceRequest.getDeviceId().isEmpty()){
			response.setResponseCode(400);
			response.setResponseMessage("Please choose atleast 1 Device(s).");
		}
		else if (priceRequest.getStartDate().after(priceRequest.getEndDate())){
			response.setResponseCode(400);
			response.setResponseMessage("Please select End date after Start date");
		}
		else if(!value.equalsIgnoreCase("true"))
		{
			response.setResponseCode(400);
			response.setResponseMessage("Please Schedule campaign before "+value);
		}
		else if(priceRequest.getStartDate().getDate() == (new Date().getDate()) && priceRequest.getStartDate().getMonth() == new Date().getMonth()){
			response.setResponseCode(400);
			response.setResponseMessage("Start Date cannot be Today's Date");
		}
		else{
			
			List<Device> category= deviceService.getPrice(priceRequest.getDeviceId()); 
			Map<Integer,Double> pricePerDevice = new  HashMap<Integer,Double>();
			int maxSlotsPerDay=0;
			double price =0.0d;
			for (Device device : category) {
				maxSlotsPerDay=maxAvailableSlotsPerDay(device.getDeviceCategory());
				pricePerDevice.put(device.getId(), device.getDeviceCategory().getNumberOfTimesPlayed() * device.getDeviceCategory().getSecondsPlayed() * device.getDeviceCategory().getPrice());
			}
			FullSlots slots=getSlots(priceRequest.getStartDate(), priceRequest.getEndDate(), priceRequest.getDeviceId(),maxSlotsPerDay);
			int workingDays=getWorkingDaysBetween(priceRequest.getStartDate(), priceRequest.getEndDate());
			double freeWorkingDays=0;
			for (Integer device : priceRequest.getDeviceId()) {
				if(null != slots.getDateMap() && null != slots.getDateMap().get(device))
					freeWorkingDays=workingDays-slots.getDateMap().get(device).size();
				else
					freeWorkingDays=workingDays;
				price+=pricePerDevice.get(device)*freeWorkingDays;
			}
			if(price <=0 && slots.getDateMap().size()>0){
				response.setPrice(price);
				response.setDatesNotAvailable(slots);
				response.setResponseCode(400);
				response.setResponseMessage("All Slots for the above devices with dates are Full. \n Please try other dates");
			}else{
			response.setPrice(price);
			response.setDatesNotAvailable(slots);
			response.setResponseCode(200);
			response.setResponseMessage("Prices are excluding Weekends and Slots Available between above Dates");
			}
		}
		return response;
	}
	
	private String getDateAfter1Month(Date endDate ){
		Calendar start= Calendar.getInstance();
		start.setTime(new Date());
		Calendar end= Calendar.getInstance();
		end.setTime(endDate);
		
		start.add(Calendar.DATE, 1);
		start.add(Calendar.MONTH, 1);
		start.set(Calendar.HOUR, 0);
		start.set(Calendar.MINUTE, 0);
		start.set(Calendar.SECOND, 0);
			if(start.before(end))
				return start.getTime().toString();
		return "true";
	}
	
	private Response saveContents(ContentRequest contentRequest, Response response){
		ContentPlayingNow now ;
		try {
		for (Integer deviceId : contentRequest.getDeviceId()) {
			Device device = deviceService.findDeviceById(deviceId);
			UserDocument document = userDocumentService.findById(contentRequest.getContnetId());
			int maxSlotsPerDay=maxAvailableSlotsPerDay(device.getDeviceCategory());
					FullSlots slots=getSlots(contentRequest.getStartDate(), contentRequest.getEndDate(), contentRequest.getDeviceId(),maxSlotsPerDay);
					List<String> date=getListOfDaysBetweenTwoDates(contentRequest.getStartDate(), contentRequest.getEndDate());
				for (String string : date) {
					if(null != slots.getDateMap() && null != slots.getDateMap().get(deviceId) && slots.getDateMap().get(deviceId).contains(string));
					//	if(slots.getDateMap().get(deviceId).contains(string))
							//	System.out.println("NOT AVAILABLE"+string);
						else{
							now = new ContentPlayingNow();
							now.setDelay(getDelay(device.getDeviceCategory()));
							now.setDelayUnit("SEC");
							now.setDevice(device);
							String startTime=setStartTime(device.getDeviceCategory(), string, deviceId);
							String endTime=getDateFormatter().format(getDateFormatter().parse(startTime).getTime()+30000);
							now.setEndTime(endTime);
							now.setStartTime(startTime);
							now.setCampaignPrice(contentRequest.getPrice());
							now.setUserDocument(document);
							now.setIsActive(1);
							now.setIsDeleted(0);
							now.setGroupId(EncryptUtils.base64encode(contentRequest.getEndDate().toString()+contentRequest.getStartDate().toString()+document.getName()));
							contentPlayingNowService.save(now);
						}
							}
				}
		response.setResponseCode(200);
		response.setResponseMessage("Inserted Succesfully");
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setResponseCode(401);
			response.setResponseMessage("Something Went Wrong!! Please try after sometime.");
		}
		
		return response;
	}
	private int getWorkingDaysBetween(Date startDate, Date endDate) {
	    int workingDays = 0;
	    try
	    {
	      Calendar start = Calendar.getInstance();
	      start.setTime(startDate);
	      Calendar end = Calendar.getInstance();
	      end.setTime(endDate);
	      
	      while(!start.after(end))
	      {
	        //int day = start.getDay();
	        int day = start.get(Calendar.DAY_OF_WEEK);
	        //if ((day != Calendar.SATURDAY) || (day != Calendar.SUNDAY)) if it's sunday, != to Saturday is true
	        if ((day != Calendar.SATURDAY) && (day != Calendar.SUNDAY))
	        workingDays++;
	        start.add(Calendar.DATE, 1);
	      }
	    }
	    catch(Exception e)
	    {
	      e.printStackTrace();
	    }
	    return workingDays;
	}
	
	/*private int slotBetweenCampaign(DeviceCategory category){
		return (60/maxAvailableSlotsPerDay(category))*60;
	}*/
	
	private int maxAvailableSlotsPerDay(DeviceCategory category){
		int secPerDay=category.getWorkingHours()*60*60;
		return secPerDay/(category.getNumberOfTimesPlayed()*category.getSecondsPlayed());
	}
	
	private String setStartTime(DeviceCategory category, String date,int deviceId ){
		int count=contentPlayingNowService.getTotalSlotsInDay(date, deviceId);
		Date d=null;
		try {
			 d = getDateFormatter().parse(date);
			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
			cal.add(Calendar.HOUR, category.getDayStartHour());
			cal.add(Calendar.SECOND, (count + 1)* 30);
			 d=cal.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getDateFormatter().format(d);
	}
	
	private FullSlots getSlots(Date startDate,Date endDate,List<Integer> deviceId, int maxSlot){
		List<String> dateList = getListOfDaysBetweenTwoDates(startDate, endDate);
		FullSlots sf = new FullSlots();;
		 Map<Integer,List<String>> dateMap = new HashMap<Integer,List<String>>();
		 Map<Integer,String> deviceNameMap = new HashMap<Integer,String>();
		List<String> datesFull=null;
		for (Integer device : deviceId) {
			datesFull=new ArrayList<String>();
			for (String date : dateList) {
				int no=contentPlayingNowService.getTotalSlotsInDay(date,device);
				if(no>maxSlot)
					datesFull.add(date);
			}
			
			if(!datesFull.isEmpty()){
			dateMap.put(device, datesFull);
			sf.setDateMap(dateMap);
			Device devicedetails=deviceService.findDeviceById(device);
			deviceNameMap.put(device, devicedetails.getDeviceLocation().getDevceLocationName()+" - "+ devicedetails.getDeviceName());
			sf.setDeviceNameMap(deviceNameMap);
			}
		}
		return sf;
	}
	
	private List<String> getListOfDaysBetweenTwoDates(Date startDate, Date endDate)  {
	    List<String> result = new ArrayList<String>();
	    Calendar start = Calendar.getInstance();
	    start.setTime(startDate);
	    Calendar end = Calendar.getInstance();
	    end.setTime(endDate);
	    end.add(Calendar.DAY_OF_YEAR, 1); //Add 1 day to endDate to make sure endDate is included into the final list
	    while (start.before(end)) {
	    	 int day = start.get(Calendar.DAY_OF_WEEK);
		        if ((day != Calendar.SATURDAY) && (day != Calendar.SUNDAY))
		        		result.add(getDateFormatter().format(start.getTime()));
	        start.add(Calendar.DAY_OF_YEAR, 1);
	    }
	    return result;
	}
	
	public DateFormat getDateFormatter(){
		return new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	}
	
	private int getDelay(DeviceCategory category){
		return maxAvailableSlotsPerDay(category) * category.getSecondsPlayed();
	}
	
	
}
