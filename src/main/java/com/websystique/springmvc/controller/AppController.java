package com.websystique.springmvc.controller;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.nio.file.Files;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.websystique.springmvc.configuration.ApplicationInitializer;
import com.websystique.springmvc.model.ClientContents;
import com.websystique.springmvc.model.ContentPlayingNow;
import com.websystique.springmvc.model.FileBucket;
import com.websystique.springmvc.model.RegisterUser;
import com.websystique.springmvc.model.User;
import com.websystique.springmvc.model.UserDocument;
import com.websystique.springmvc.service.ContentPlayingNowService;
import com.websystique.springmvc.service.RegisterUserService;
import com.websystique.springmvc.service.UserDocumentService;
import com.websystique.springmvc.service.UserService;
import com.websystique.springmvc.util.EncryptUtils;
import com.websystique.springmvc.util.FileValidator;



@Controller
@SessionAttributes("roles")
public class AppController {
	static final Logger logger = LoggerFactory.getLogger(AppController.class);
	@Autowired
	UserService userService;
	
	@Autowired
	UserDocumentService userDocumentService;
	
	@Autowired
	MessageSource messageSource;

	@Autowired
	FileValidator fileValidator;
	
	@Autowired
	ContentPlayingNowService contentPlayingNowService;
	
	@Autowired
	RegisterUserService registerUserService;
	
	@InitBinder("fileBucket")
	protected void initBinder(WebDataBinder binder) {
	   binder.setValidator(fileValidator);
	}
	
	/**
	 * This method will list all existing users.
	 */
	@RequestMapping(value = { "/companylist-{registerUser}" }, method = RequestMethod.GET)
	public String listUsers(ModelMap model,@PathVariable Integer registerUser) {
		//List<User> users = userService.findAllUsers();
		List<User> users = userService.findAllUsers(registerUser);
		model.addAttribute("users", users);
		model.addAttribute("register", registerUser);
		return "/companylist";
	}

	@RequestMapping(value = "/testn", method = {RequestMethod.POST},headers="Accept=application/json")
	public String test(@RequestParam  String cityName){
		return cityName;
	}
	
	/**
	 * This method will provide the medium to add a new user.
	 */
	@RequestMapping(value = { "/newcompany-{registerUser}" }, method = RequestMethod.GET)
	public String newUser(ModelMap model,@PathVariable Integer registerUser) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("edit", false);
		model.addAttribute("register",registerUser);
		return "companyregistration";
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * saving user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/newcompany-{register}" }, method = RequestMethod.POST)
	public String saveUser(@Valid User user, BindingResult result,@PathVariable Integer register,
			ModelMap model) {

		if (result.hasErrors()) {
			return "companyregistration";
		}

		/*
		 * Preferred way to achieve uniqueness of field [sso] should be implementing custom @Unique annotation 
		 * and applying it on field [sso] of Model class [User].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 * 
		 */
		if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
			FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
		    result.addError(ssoError);
			return "companyregistration";
		}
		RegisterUser registerUsers=registerUserService.findById(register);
		user.setIsDeleted(1);
		user.setRegisterUser(registerUsers);
		userService.saveUser(user);
		
		model.addAttribute("user", user);
		model.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " registered successfully");
		return "companyregistrationsuccess";
	}


	/**
	 * This method will provide the medium to update an existing user.
	 */
	@RequestMapping(value = { "/edit-company-{ssoId}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable String ssoId, ModelMap model) {
		User user = userService.findBySSO(ssoId);
		model.addAttribute("user", user);
		model.addAttribute("edit", true);
		return "companyregistration";
	}
	
	/**
	 * This method will be called on form submission, handling POST request for
	 * updating user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-company-{ssoId}" }, method = RequestMethod.POST)
	public String updateUser(@Valid User user, BindingResult result,
			ModelMap model, @PathVariable String ssoId) {

		if (result.hasErrors()) {
			logger.info(result.getFieldError().toString());
			return "companyregistration";
		}
		user.setIsDeleted(1);
		userService.updateUser(user,1);

		model.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " updated successfully");
		return "companyregistrationsuccess";
	}

	
	/**
	 * This method will delete an user by it's SSOID value.
	 */
	@RequestMapping(value = { "/delete-company-{ssoId}-{registerUserId}" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable String ssoId,@PathVariable Integer registerUserId) {
		//userService.deleteUserBySSO(ssoId);
		User user=userService.findBySSO(ssoId);
		userService.updateUser(user, 0);
		return "redirect:/companylist-"+registerUserId;
	}
	

	
	@RequestMapping(value = { "/add-document-{userId}" }, method = RequestMethod.GET)
	public String addDocuments(@PathVariable int userId, ModelMap model) {
		User user = userService.findById(userId);
		model.addAttribute("user", user);

		FileBucket fileModel = new FileBucket();
		model.addAttribute("fileBucket", fileModel);

		List<UserDocument> documents = userDocumentService.findAllByUserId(userId);
		model.addAttribute("documents", documents);
		model.addAttribute("user", user);
		return "managedocuments";
	}
	

	@RequestMapping(value = { "/download-document-{userId}-{docId}" }, method = RequestMethod.GET)
	public String downloadDocument(@PathVariable int userId, @PathVariable int docId, HttpServletResponse response) throws IOException {
		UserDocument document = userDocumentService.findById(docId);
		File f=null;
		f= new File(document.getFileLocation()+File.separator+document.getName());
		response.setContentType(document.getType());
        response.setContentLength(Files.readAllBytes(f.toPath()).length);
        response.setHeader("Content-Disposition","attachment; filename=\"" + document.getName() +"\"");
 
        FileCopyUtils.copy(Files.readAllBytes(f.toPath()), response.getOutputStream());
        response.flushBuffer();
 
 		return "redirect:/add-document-"+userId;
	}
	
	/*@RequestMapping(value = { "/content" }, method = RequestMethod.GET)
	public String getdownloadContent(@PathVariable int userId, @PathVariable int docId, HttpServletResponse response,HttpServletRequest httpServletRequest) throws IOException {
		UserDocument document = userDocumentService.findById(1);
		File f=null;
		String a=httpServletRequest.getParameter("ABC");
		System.out.println(a);
		f= new File(document.getFileLocation()+File.separator+document.getName());
		 response.setContentType(document.getType());
	        response.setContentLength(Files.readAllBytes(f.toPath()).length);
	        response.setHeader("Content-Disposition","attachment; filename=\"" + document.getName() +"\"");
	 
	        FileCopyUtils.copy(Files.readAllBytes(f.toPath()), response.getOutputStream());
 
 		return "trnsfrered";
	}*/
	
	@RequestMapping(value = { "/content/{id}/{date}" }, method = RequestMethod.GET)
	public String getdownloadContent(@PathVariable int id,@PathVariable String date,@Valid ClientContents clientContents, HttpServletResponse response,HttpServletRequest httpServletRequest) throws IOException {
		List<ContentPlayingNow> contentPlayingNow = contentPlayingNowService.findByDeviceId(id);
		File f=null;
		for (ContentPlayingNow contentPlayingNow2 : contentPlayingNow) {
			f= new File(contentPlayingNow2.getUserDocument().getFileLocation()+File.separator+contentPlayingNow2.getUserDocument().getName());
			 	response.setContentType(contentPlayingNow2.getUserDocument().getType());
		        response.setContentLength(Files.readAllBytes(f.toPath()).length);
		        response.setHeader("Content-Disposition","attachment; filename=\"" + contentPlayingNow2.getUserDocument().getName() +"\"");
		 
		        FileCopyUtils.copy(Files.readAllBytes(f.toPath()), response.getOutputStream());
		        response.flushBuffer();
	 
		}
		
 		return "trnsfrered";
	}

	@RequestMapping(value = { "/delete-document-{userId}-{docId}" }, method = RequestMethod.GET)
	public String deleteDocument(@PathVariable int userId, @PathVariable int docId) {
		System.out.println(docId);
		userDocumentService.deleteById(docId);
		return "redirect:/add-document-"+userId;
	}

	@RequestMapping(value = { "/add-document-{userId}" }, method = RequestMethod.POST)
	public String uploadDocument(@Valid FileBucket fileBucket, BindingResult result, ModelMap model, @PathVariable int userId) throws IOException{
		logger.info("Adding Document");
		if (result.hasErrors()) {
			User user = userService.findById(userId);
			model.addAttribute("user", user);

			List<UserDocument> documents = userDocumentService.findAllByUserId(userId);
			model.addAttribute("documents", documents);
			
			return "managedocuments";
		} else {
			
			User user = userService.findById(userId);
			model.addAttribute("user", user);
			
			saveDocument(fileBucket, user);

			return "redirect:/add-document-"+userId;
		}
	}
	
	@RequestMapping(value = { "/reports-{userId}" }, method = RequestMethod.GET)
	public String report(@PathVariable int userId, ModelMap model) throws IOException {
		//UserDocument document = userDocumentService.findById(docId);
		List<ContentPlayingNow> documents = contentPlayingNowService.getContent(userId);
		Collections.sort(documents,new Content());
		model.addAttribute("documents", documents);
 		return "report";
	}
	
	
	private void saveDocument(FileBucket fileBucket, User user) throws IOException{
		logger.info("Saving Document for the user");
		UserDocument document = new UserDocument();
		
		MultipartFile multipartFile = fileBucket.getFile();
		multipartFile.transferTo(new File(multipartFile.getOriginalFilename()));
		//fileBucket.setFile(null);
		document.setName(multipartFile.getOriginalFilename());
		document.setDescription("Please call 9945672422");
		document.setType(multipartFile.getContentType());
		document.setContent(null);
		document.setUser(user);
		document.setFileLocation(ApplicationInitializer.LOCATION);
		document.setUniqueIdentifier(EncryptUtils.base64encode(multipartFile.getOriginalFilename()));
		document.setPlayGroup(1);
		document.setIsDeleted(1);
		userDocumentService.saveDocument(document);
	}
	
	class Content implements Comparator<ContentPlayingNow>{

		@Override
		public int compare(ContentPlayingNow arg0, ContentPlayingNow arg1) {
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			int axe=0;
			try {
				axe= formatter.parse(arg0.getStartTime()).compareTo(formatter.parse(arg1.getStartTime()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return -axe;
		}
		
		
		
	}
}
