package io.javabrains.springbootstarter.hero;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/heroes")
public class HeroRestController {
	
	@Autowired
	HeroRepository heroRepository;
	
	@GetMapping
	public Iterable<Hero> getHeroes(){
		return heroRepository.findAll();
	}
	
	@PostMapping
	public Hero addHero(@RequestBody Hero hero) {
		return heroRepository.save(hero);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity <?> getHeroById(@PathVariable int id) {
		return heroRepository.findById(id)
				.map(hero -> ResponseEntity.ok().body(hero))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity <?> updateHero(@PathVariable int id, @RequestBody Hero heroUp) {
		return heroRepository.findById(id)
				.map(hero -> {
					hero.setNome(heroUp.getNome());
					Hero updated = heroRepository.save(hero);
					return ResponseEntity.ok().body(updated);
				}).orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity <?> deleteHero(@PathVariable int id) {
		return heroRepository.findById(id)
				.map(hero -> {
					heroRepository.deleteById(id);
					return ResponseEntity.ok().build();
				}).orElse(ResponseEntity.notFound().build());
	}

}
