package io.javabrains.springbootstarter.hero;

import org.springframework.data.repository.CrudRepository;

public interface HeroRepository extends CrudRepository<Hero, Integer> {

}
