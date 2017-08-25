@Controller
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping("/user")
	public String usuario(Model model){
		Usuario usuario = new Usuario();

		if(usuarioRepository.findOne(1) == null){
			model.addAttribute("name","Es puto nulo");
			return "usuario";
		}
		usuario = usuarioRepository.findOne(1);
		//usuario.setNombre("Pepe");

		model.addAttribute(usuario);
		return "usuario";
	}


	@PostMapping(value="/userPost")
	//post solo formularios, get para todo
	public String displayUsuarioPage (Model model){
		Usuario usuario = new Usuario();

		//usuarioMapper.selectUsuario(0);
		usuario.setNombre("Pepe");

		model.addAttribute("name",usuario.getNombre());

		return "usuario";

	}


}
