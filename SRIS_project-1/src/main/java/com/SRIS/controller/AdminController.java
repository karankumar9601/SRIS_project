package com.SRIS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.SRIS.Model.AboutUsForm;
import com.SRIS.Model.CourseForm;
import com.SRIS.Model.ImageForm;
import com.SRIS.Model.InstructorForm;
import com.SRIS.Model.LogoForm;
import com.SRIS.Model.TestimonialForm;
import com.SRIS.Model.UploadProjectForm;
import com.SRIS.service.AboutUsFormService;
import com.SRIS.service.AdminCredentialService;
import com.SRIS.service.ContactFromService;
import com.SRIS.service.CourseFormService;
import com.SRIS.service.ImageFormService;
import com.SRIS.service.InstructorFormService;
import com.SRIS.service.LogoFormService;
import com.SRIS.service.TestimonialFormService;
import com.SRIS.service.UploadProjectFormService;

@Controller
@RequestMapping("admin")
public class AdminController {

	private ContactFromService contactFromService;
	private AdminCredentialService adminCredentialService;
	private CourseForm courseForm;
	private CourseFormService courseFormService;
	private TestimonialForm testimonialForm;
	private TestimonialFormService testimonialFormService;
	private ImageForm imageForm;
	private ImageFormService imageFormService;
	private UploadProjectForm uploadProjectForm;
	private UploadProjectFormService uploadProjectFormService;
	private AboutUsForm aboutUsForm;
	private AboutUsFormService aboutUsFormService;
	private InstructorForm instructorForm;
	private InstructorFormService instructorFormService;
	private LogoForm logoForm;
	private LogoFormService logoFormService;

	@Autowired
	public void setLogoFormService(LogoFormService logoFormService) {
		this.logoFormService = logoFormService;
	}

	@Autowired
	public void setLogoForm(LogoForm logoForm) {
		this.logoForm = logoForm;
	}

	@Autowired
	public void setInstructorFormService(InstructorFormService instructorFormService) {
		this.instructorFormService = instructorFormService;
	}

	@Autowired
	public void setInstructorForm(InstructorForm instructorForm) {
		this.instructorForm = instructorForm;
	}

	@Autowired
	public void setAboutUsForm(AboutUsForm aboutUsForm) {
		this.aboutUsForm = aboutUsForm;
	}

	@Autowired
	public void setAboutUsFormService(AboutUsFormService aboutUsFormService) {
		this.aboutUsFormService = aboutUsFormService;
	}

	@Autowired
	public void setUploadProjectForm(UploadProjectForm uploadProjectForm) {
		this.uploadProjectForm = uploadProjectForm;
	}

	@Autowired
	public void setUploadProjectFormService(UploadProjectFormService uploadProjectFormService) {
		this.uploadProjectFormService = uploadProjectFormService;
	}

	@Autowired
	public void setImageForm(ImageForm imageForm) {
		this.imageForm = imageForm;
	}

	@Autowired
	public void setImageFormService(ImageFormService imageFormService) {
		this.imageFormService = imageFormService;
	}

	@Autowired
	public void setTestimonialForm(TestimonialForm testimonialForm) {
		this.testimonialForm = testimonialForm;
	}

	@Autowired
	public void setTestimonialFormService(TestimonialFormService testimonialFormService) {
		this.testimonialFormService = testimonialFormService;
	}

	@Autowired
	public void setCourseFormService(CourseFormService courseFormService) {
		this.courseFormService = courseFormService;
	}

	@Autowired
	public void setCourseForm(CourseForm courseForm) {
		this.courseForm = courseForm;
	}

	@Autowired
	public void setAdminCredentialService(AdminCredentialService adminCredentialService) {
		this.adminCredentialService = adminCredentialService;
	}

	@Autowired
	public void setContactFromService(ContactFromService contactFromService) {
		this.contactFromService = contactFromService;
	}

	@GetMapping("dashboard")
	public String adminDashboard() {

		return "admin/dashboard";
	}

	@GetMapping("readContact")
	public String readContact(Model model) {

		model.addAttribute("allContact", contactFromService.readContactService());
		return "admin/readContact";
	}

	@GetMapping("deleteContact/{id}")
	public String deleteContact(@PathVariable int id, RedirectAttributes redirectAttributes) {
		contactFromService.deleteContactService(id);
		redirectAttributes.addFlashAttribute("message", "contact deleted Successfully");
		return "redirect:/admin/readContact";
	}

	@GetMapping("changeCredentials")
	public String changeCredentialsView() {
		return "admin/changeCredentials";
	}

	@PostMapping("changeCredentials")
	public String changeCredentials(@RequestParam("oldusername") String oldusername,
			@RequestParam("oldpassword") String oldpassword, @RequestParam("newusername") String newusername,
			@RequestParam("newpassword") String newpassword, RedirectAttributes redirectAttributes) {
		String result = adminCredentialService.checkAdminCredential(oldusername, oldpassword);
		// System.out.println(result);
		if (result.equals("SUCCESS")) {
			// update
			result = adminCredentialService.updateAdminCredential(newusername, newpassword, oldusername);
			redirectAttributes.addFlashAttribute("message", result);
		} else {
			redirectAttributes.addFlashAttribute("message", result);
		}
		return "redirect:/admin/changeCredentials";
	}

	@GetMapping("addCourse")
	public String addCourseView() {

		return "admin/addCourse";
	}

	@InitBinder
	public void stopBinder(WebDataBinder webDataBinder) {
		webDataBinder.setDisallowedFields("image");
	}

	@PostMapping("addCourse")
	public String addCourse(@ModelAttribute CourseForm courseForm, @RequestParam("image") MultipartFile multipartFile,
			RedirectAttributes redirectAttributes) {
		String originalFilename = multipartFile.getOriginalFilename();
		courseForm.setImage(originalFilename);
		// service layer
		try {
			CourseForm course = courseFormService.addCourse(courseForm, multipartFile);
			if (course != null) {
				redirectAttributes.addFlashAttribute("message", "Course Added Successfully");
			} else {
				redirectAttributes.addFlashAttribute("message", "Something Went Wrong");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "redirect:/admin/addCourse";
	}

	@GetMapping("readCourse")
	public String readCourseView(Model model) {
		// model.addAttribute("allCourse",courseFormService.readAllCourse());
		List<CourseForm> allCourse = courseFormService.readAllCourse();
		model.addAttribute("allCourse", allCourse);
		return "admin/readCourse";
	}

	@GetMapping("deleteCourse/{id}")
	public String deleteCourse(@PathVariable int id, RedirectAttributes redirectAttributes) {
		courseFormService.deleteCourse(id);
		redirectAttributes.addFlashAttribute("message", "Course deleted Successfully");
		return "redirect:/admin/readCourse";
	}

	@GetMapping("addTestimonial")
	public String addTestimonialView() {

		return "admin/addTestimonial";

	}

	@PostMapping("addTestimonial")
	public String addTestimonial(@ModelAttribute TestimonialForm testimonialForm,
			@RequestParam("image") MultipartFile multipartFile, RedirectAttributes redirectAttributes) {
		String originalFilename = multipartFile.getOriginalFilename();
		testimonialForm.setImage(originalFilename);

		try {
			TestimonialForm testimonial = testimonialFormService.addTestimonial(testimonialForm, multipartFile);
			if (testimonial != null) {
				redirectAttributes.addFlashAttribute("message", "Testimonial Added Successfully");
			} else {
				redirectAttributes.addFlashAttribute("message", "SomeThing wen wrong");
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return "redirect:/admin/addTestimonial";
	}

	@GetMapping("readTestimonial")
	public String readTestimonialView(Model model) {

		List<TestimonialForm> allTestimonial = testimonialFormService.readAllTestimonial();
		model.addAttribute("allTestimonial", allTestimonial);
		return "admin/readTestimonial";
	}

	@GetMapping("deleteTestimonial/{id}")
	public String deleteTestimonial(@PathVariable int id, RedirectAttributes redirectAttributes) {
		testimonialFormService.deleteTestimonial(id);
		redirectAttributes.addFlashAttribute("message", "Testimonial deleted Successfully");
		return "redirect:/admin/readTestimonial";
	}

	@GetMapping("addImage")
	public String addImageView() {
		return "admin/addImage";
	}

	@PostMapping("addImage")
	public String addImage(@ModelAttribute ImageForm imageForm, @RequestParam("image") MultipartFile multipartFile,
			RedirectAttributes redirectAttributes) {
		String originalFilename = multipartFile.getOriginalFilename();

		imageForm.setImage(originalFilename);
		try {
			ImageForm image = imageFormService.addImage(imageForm, multipartFile);
			if (image != null) {
				redirectAttributes.addFlashAttribute("message", "Image Uploaded Successfully");
			} else {
				redirectAttributes.addFlashAttribute("message", "Something Went worng");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "redirect:/admin/addImage";
	}

	@GetMapping("readImage")
	public String readImage(Model model) {
		List<ImageForm> allImage = imageFormService.readAllImage();
		System.out.println(allImage);
		model.addAttribute("allImage", allImage);
		return "admin/readImage";
	}

	@GetMapping("deleteImage/{id}")
	public String deleteImage(@PathVariable int id, RedirectAttributes redirectAttributes) {
		imageFormService.deleteImage(id);
		redirectAttributes.addFlashAttribute("message", "Image deleted Successfully");
		return "redirect:/admin/readImage";
	}

	@GetMapping("uploadProject")
	public String uploadProjectView() {
		return "admin/uploadProject";
	}

	@PostMapping("uploadProject")
	public String uploadProject(@ModelAttribute UploadProjectForm uploadProjectForm,
			@RequestParam("image") MultipartFile multipartFile, RedirectAttributes redirectAttributes) {
		String originalFilename = multipartFile.getOriginalFilename();
		uploadProjectForm.setImage(originalFilename);
		try {
			UploadProjectForm project = uploadProjectFormService.addProject(multipartFile, uploadProjectForm);
			if (project != null) {
				redirectAttributes.addFlashAttribute("message", "Project Uploaded Successfully");
			} else {
				redirectAttributes.addFlashAttribute("message", "Something Went Worng");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/admin/uploadProject";
	}

	@GetMapping("readProject")
	public String readProject(Model model) {
		List<UploadProjectForm> allProject = uploadProjectFormService.readAllProject();
		model.addAttribute("allProject", allProject);
		return "admin/readProject";
	}

	@GetMapping("deleteProject/{id}")
	public String deleteProject(@PathVariable int id, RedirectAttributes redirectAttributes) {
		uploadProjectFormService.deleteProject(id);
		redirectAttributes.addFlashAttribute("message", " Project Deleted Successfully");
		return "redirect:/admin/readProject";

	}

	@GetMapping("addAboutUs")
	public String addAboutUsView() {
		return "admin/addAboutUs";
	}

	@PostMapping("addAboutUs")
	public String addAboutUs(@ModelAttribute AboutUsForm aboutUsForm,
			@RequestParam("image") MultipartFile multipartFile, RedirectAttributes redirectAttributes) {
		String originalFilename = multipartFile.getOriginalFilename();
		aboutUsForm.setImage(originalFilename);
		try {
			AboutUsForm aboutUs = aboutUsFormService.addAboutUs(multipartFile, aboutUsForm);
			// System.out.println("Saving on Admin page"+aboutUs);
			if (aboutUs != null) {
				redirectAttributes.addFlashAttribute("message", "AboutUs Added Successfully");
			} else {
				redirectAttributes.addFlashAttribute("message", "Smoething Went Worng");
			}
		} catch (Exception e) {

		}

		return "redirect:/admin/addAboutUs";
	}

	@GetMapping("readAboutUs")
	public String readAboutUs(Model model) {
		List<AboutUsForm> readallAboutUs = aboutUsFormService.readallAboutUs();
		model.addAttribute("allAboutUs", readallAboutUs);
		return "admin/readAboutUs";
	}

	@GetMapping("deleteAboutUs/{id}")
	public String deleteAboutUs(@PathVariable int id, RedirectAttributes redirectAttributes) {
		aboutUsFormService.deleteAllAboutUs(id);
		redirectAttributes.addFlashAttribute("message", "AboutUs Deleted Successfully");
		return "redirect:/admin/readAboutUs";
	}

	@GetMapping("addInstructors")
	public String addInstructorsView() {
		return "admin/addInstructors";
	}

	@PostMapping("addInstructors")
	public String addInstructors(@ModelAttribute InstructorForm instructorForm,
			@RequestParam("image") MultipartFile multipartFile, RedirectAttributes redirectAttributes) {
		String originalFilename = multipartFile.getOriginalFilename();
		instructorForm.setImage(originalFilename);
		try {
			InstructorForm instructorDetails = instructorFormService.addInstructorDetails(multipartFile,
					instructorForm);
			redirectAttributes.addFlashAttribute("message", "Instructors Details Successfully Added");

		} catch (Exception e) {

		}

		return "redirect:/admin/addInstructors";
	}

	@GetMapping("readInstructor")
	public String readInstructor(Model model) {
		List<InstructorForm> allInstructors = instructorFormService.readAllInstructors();
		model.addAttribute("allInstructors", allInstructors);
		return "admin/readInstructor";
	}

	@GetMapping("deleteInstructor/{id}")
	public String deleteInstructor(@PathVariable int id, RedirectAttributes redirectAttributes) {
		instructorFormService.deleteInstructorsDetails(id);
		redirectAttributes.addFlashAttribute("message", "Instructor Details Deleted");
		return "redirect:/admin/readInstructor";
	}

	@GetMapping("addlogo")
	public String addlogoView() {
		return "admin/addlogo";
	}

	@PostMapping("addLogo")
	public String addLogo(@ModelAttribute LogoForm logoForm, @RequestParam("image") MultipartFile multipartFile,
			RedirectAttributes redirectAttributes) {
		String originalFilename = multipartFile.getOriginalFilename();
		logoForm.setImage(originalFilename);
		try {
			LogoForm logo = logoFormService.addLogo(multipartFile, logoForm);
			if (logo != null) {
				redirectAttributes.addFlashAttribute("message", "Logo Added Successfully");
			}
		} catch (Exception e) {

		}

		return "redirect:/admin/addlogo";
	}

	@GetMapping("readLogo")
	public String readLogo(Model model) {
		List<LogoForm> logo = logoFormService.readLogo();
		model.addAttribute("logo", logo);
		return "admin/readLogo";
	}
	
	@GetMapping("deleteLogo/{id}")
	public String deleteLogo(@PathVariable int id, RedirectAttributes redirectAttributes) {
		logoFormService.deleteLogo(id);
		redirectAttributes.addFlashAttribute("message", "Logo  Deleted Successfully");
		return "redirect:/admin/readLogo";
	}
}
