package ats.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ats.dao.ApplyDao;
import ats.model.Job;
import ats.service.CvService;

@Transactional
@Service
public class CvServiceImpl implements CvService {
	@Autowired
	private ApplyDao applydao;

	public ApplyDao getApplydao() {
		return applydao;
	}

	public void setApplydao(ApplyDao applydao) {
		this.applydao = applydao;
	}

	@Override
	public void addCv(ats.model.CV file, Job job) {
		applydao.uploadCv(file, job);
	}

}
