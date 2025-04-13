package com.SRIS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.SRIS.Model.AboutUsForm;
import com.SRIS.Model.ContactDto;
import com.SRIS.Model.CourseForm;
import com.SRIS.Model.ImageForm;
import com.SRIS.Model.InstructorForm;
import com.SRIS.Model.LogoForm;
import com.SRIS.Model.TestimonialForm;
import com.SRIS.Model.UploadProjectForm;
import com.SRIS.service.AboutUsFormService;
import com.SRIS.service.ContactFromService;
import com.SRIS.service.CourseFormService;
import com.SRIS.service.ImageFormService;
import com.SRIS.service.InstructorFormService;
import com.SRIS.service.LogoFormService;
import com.SRIS.service.TestimonialFormService;
import com.SRIS.service.UploadProjectFormService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MyController {

	private ContactFromService contactFromService;
	private CourseFormService courseFormService;
	private TestimonialFormService testimonialFormService;
	private ImageFormService imageFormService;
	private UploadProjectFormService uploadProjectFormService;
	private AboutUsFormService aboutUsFormService;
	private InstructorFormService instructorFormService;
	private LogoFormService logoFormService;
	
	
	
	@Autowired
    public void setLogoFormService(LogoFormService logoFormService) {
		this.logoFormService = logoFormService;
	}

	@Autowired
	public void setInstructorFormService(InstructorFormService instructorFormService) {
		this.instructorFormService = instructorFormService;
	}

	@Autowired
	public void setAboutUsFormService(AboutUsFormService aboutUsFormService) {
		this.aboutUsFormService = aboutUsFormService;
	}

	@Autowired
	public void setUploadProjectFormService(UploadProjectFormService uploadProjectFormService) {
		this.uploadProjectFormService = uploadProjectFormService;
	}

	@Autowired
	public void setImageFormService(ImageFormService imageFormService) {
		this.imageFormService = imageFormService;
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
	public void setContactFromService(ContactFromService contactFromService) {
		this.contactFromService = contactFromService;
	}

	@GetMapping(path = { "/", "index", "home" })
	public String welcomeView(Model model) {
		List<TestimonialForm> allTestimonial = testimonialFormService.readAllTestimonial();
		model.addAttribute("allTestimonial", allTestimonial);
		List<ImageForm> allImage = imageFormService.readAllImage();
		model.addAttribute("allImage", allImage);
		List<UploadProjectForm> allProject = uploadProjectFormService.readAllProject();
		// System.out.println(allProject);
		model.addAttribute("allProject", allProject);
		List<AboutUsForm> readallAboutUs = aboutUsFormService.readallAboutUs();
		model.addAttribute("allAboutUs", readallAboutUs);
		List<CourseForm> allCourse = courseFormService.readAllCourse();
		model.addAttribute("allCourse", allCourse);
		List<LogoForm> logo = logoFormService.readLogo();
		model.addAttribute("logo", logo);

		return "index";

	}

	@GetMapping("about")
	public String about(Model model) {
		List<UploadProjectForm> allProject = uploadProjectFormService.readAllProject();
		model.addAttribute("allProject", allProject);
		List<AboutUsForm> readallAboutUs = aboutUsFormService.readallAboutUs();
		model.addAttribute("allAboutUs", readallAboutUs);
		List<InstructorForm> allInstructors = instructorFormService.readAllInstructors();
		model.addAttribute("allInstructors",allInstructors);
		List<LogoForm> logo = logoFormService.readLogo();
		model.addAttribute("logo", logo);
		return "about";

	}

	@GetMapping("contact")
	public String contact(Model model) {
		List<UploadProjectForm> allProject = uploadProjectFormService.readAllProject();
		model.addAttribute("allProject", allProject);
		List<LogoForm> logo = logoFormService.readLogo();
		model.addAttribute("logo", logo);
		return "contact";

	}

	@GetMapping("courses")
	public String course(Model model) {
		// Data collect
		List<CourseForm> allCourse = courseFormService.readAllCourse();
		model.addAttribute("allCourse", allCourse);

		List<TestimonialForm> allTestimonial = testimonialFormService.readAllTestimonial();
		model.addAttribute("allTestimonial", allTestimonial);
		List<UploadProjectForm> allProject = uploadProjectFormService.readAllProject();
		model.addAttribute("allProject", allProject);
		List<LogoForm> logo = logoFormService.readLogo();
		model.addAttribute("logo", logo);

		return "courses";

	}

	@GetMapping("team")
	public String team(Model model) {
		List<UploadProjectForm> allProject = uploadProjectFormService.readAllProject();
		model.addAttribute("allProject", allProject);
		List<InstructorForm> allInstructors = instructorFormService.readAllInstructors();
		model.addAttribute("allInstructors",allInstructors);
		List<LogoForm> logo = logoFormService.readLogo();
		model.addAttribute("logo", logo);
		return "team";

	}

	@GetMapping("testimonial")
	public String testimonial(Model model) {
		List<TestimonialForm> allTestimonial = testimonialFormService.readAllTestimonial();
		model.addAttribute("allTestimonial", allTestimonial);
		List<UploadProjectForm> allProject = uploadProjectFormService.readAllProject();
		model.addAttribute("allProject", allProject);
		List<LogoForm> logo = logoFormService.readLogo();
		model.addAttribute("logo", logo);
		return "testimonial";

	}

	@GetMapping("404")
	public String error404(Model model) {
		List<UploadProjectForm> allProject = uploadProjectFormService.readAllProject();
		model.addAttribute("allProject", allProject);
		List<LogoForm> logo = logoFormService.readLogo();
		model.addAttribute("logo", logo);
		return "404";

	}

	@PostMapping("contact")
	public String contactFrom(@ModelAttribute ContactDto contactDto, Model model,
			RedirectAttributes redirectAttributes) {

		ContactDto saveContactFromService = contactFromService.saveContactFromService(contactDto);
		if (saveContactFromService != null) {
			redirectAttributes.addFlashAttribute("message", "Message Sent Successfully");
		} else {
			redirectAttributes.addFlashAttribute("message", "Something went worng");
		}
		return "redirect:/contact";

	}

	@GetMapping("/login")
	public String adminLogin(HttpServletRequest request, Model model) {
		ServletContext servletContext = request.getServletContext();
		Object attribute = servletContext.getAttribute("logout");
		if (attribute instanceof Boolean) {
			model.addAttribute("logout", attribute);
			servletContext.removeAttribute("logout");
		}

		return "adminlogin";

	}

	@GetMapping("/logout")
	public String adminLogout() {
		return "adminlogin";

	}

}
