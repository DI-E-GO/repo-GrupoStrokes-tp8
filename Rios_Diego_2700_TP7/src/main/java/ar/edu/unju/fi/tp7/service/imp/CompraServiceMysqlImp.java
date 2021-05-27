package ar.edu.unju.fi.tp7.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.tp7.model.Compra;
import ar.edu.unju.fi.tp7.repository.ICompraRepository;
import ar.edu.unju.fi.tp7.service.ICompraService;

@Service("compraServiceMysql")
public class CompraServiceMysqlImp implements ICompraService{

	@Autowired
	ICompraRepository compraRepository;
	
	@Autowired
	Compra compra;
	
	@Override
	public Compra getCompra() {
		// TODO Auto-generated method stub
		return compra;
	}

	@Override
	public void addCompra(Compra compra) {
		// TODO Auto-generated method stub
		compraRepository.save(compra);
	}

	@Override
	public List<Compra> getCompras() {
		// TODO Auto-generated method stub
		List<Compra> compras = compraRepository.findAll();
		return compras;
	}

	@Override
	public Optional<Compra> getCompra(Long id) {
		// TODO Auto-generated method stub
		return compraRepository.findById(id);
	}

	@Override
	public void eliminarCompra(Long id) {
		// TODO Auto-generated method stub
		compraRepository.deleteById(id);
	}

}
