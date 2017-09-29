package net.slipp.web;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.slipp.domain.User;
import net.slipp.domain.UserRepository;

@Controller
@RequestMapping("/users")
public class UserController {
	
	//List<User> users = new ArrayList<User>();
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/form")
	public String form(){
		return "/user/form";
	}
	
	@GetMapping("/loginForm")
	public String loginForm(){
		
		return "/user/login";
	}
	
	@PostMapping("/login")
	public String login(String userId, String password, HttpSession session){
		User user = userRepository.findByUserId(userId);
		
		if(user == null){
			System.out.println("Login failed!");
			return "redirect:/users/loginForm";
		}
		
		if(!user.getPassword().equals(password)){
			System.out.println("Login failed!");
			return "redirect:/users/loginForm";
		}
		System.out.println("Login Success!");
		session.setAttribute("sessionedId", user);
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session){
	
		session.removeAttribute("sessionedId");
		return "redirect:/";
	}
	
	@PostMapping("")
	public String user(User user){
		//users.add(user);
		userRepository.save(user);
		System.out.println("User : " + user);
		
		return "redirect:/users";
	}
	
	@GetMapping("")
	public String list(Model model){
		model.addAttribute("users", userRepository.findAll());
		
		return "/user/list";
	}
	@GetMapping("/{id}/form")
	public String updateForm(@PathVariable Long id, Model model, HttpSession session){
//		현 로인한 유저 정보를 가져온
		User tempUser = (User) session.getAttribute("sessionedId");
//		로인한 유저 정보가 없을 경우 로인폼으로 이
		if(tempUser == null){
			return "redirect:/user/loginForm";
		}
//		로그인한 유저 아이디와 브라우저에서 요청한 아이디를 비교한다.
		if(!tempUser.getId().equals(id)){
			throw new IllegalStateException("It is not accessable data!");
		}
//		User sessionedUser = tempUser;
//		로그인 아이디와 요청 아이디가 같으면 요청 아이디를 이용하여 데이터 베이스에서 사용자 정보를 가져온
		User user = userRepository.findOne(id);
		
		model.addAttribute("user", user);
		return "/user/updateForm";
	}
	
	@PutMapping("/{id}/update")
	public String userUpdate(@PathVariable Long id, User updatedUser, HttpSession session){
		
		User tempUser = (User) session.getAttribute("sessionedId");
		if(tempUser==null){
			return "redirect:/user/loginForm";
		}
		if(!id.equals(tempUser.getId())){
			throw new IllegalStateException("You cannot access the data!");
		}
		User user = userRepository.findOne(id);
		user.update(updatedUser);
		userRepository.save(user);
		return "redirect:/users";
	}
}
