package vn.iostart.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;
import vn.iostar.entity.Category;
import vn.iostar.model.CategoryModel;
import vn.iostar.service.CategoryService;
import vn.iostart.entity.CategoryEntity;
import vn.iostart.model.Todo;
import vn.iostart.service.ICategoryService;

@Controller
@RequestMapping("admin/categories")
public class CategoryController {
	@Autowired
	private ICategoryService cateService;

	@RequestMapping("")
	public String list(ModelMap model) {
		List<CategoryEntity> list = cateService.findAll();
		model.addAttribute("list", list);
		return "admin/categories/list";
	}
	
	@GetMapping("add")
	public String add(ModelMap model) {
		CategoryModel cateModel = new CategoryModel();
		cateModel.setEdit(false);
		model.addAttribute("category", cateModel);
		return "admin/categories/addOrEdit";
	}
	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model,
			@Valid @ModelAttribute("category") CategoryModel cateModel, BindingResult result) {
		if(result.hasErrors()) {
			return new ModelAndView("admin/categories/addOrEdit");
		}
		CategoryEntity entity = new CategoryEntity();
		// Từ Model sang entity
		BeanUtils.copyProperties(cateModel, entity);
		// gọi hàm save trong ser
		cateService.save(entity);
		// message
		String mess ="";
		if(cateModel.isEdit()==true) {
			mess = "Category is edited";
		}
		else {
			mess = "Category is saved";
		}
		model.addAttribute("message", mess);
		// redirect về URL controller
		return new ModelAndView("forward:/admin/categories/searchpaginated", model);
	}
	
	
	
}
