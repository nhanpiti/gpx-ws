package com.nhanpiti.gpx.ws.batchprocessing;

import com.nhanpiti.gpx.ws.entity.GpxEntity;

import com.nhanpiti.gpx.ws.entity.TrkptEntity;
import com.nhanpiti.gpx.ws.entity.WptEntity;
import com.nhanpiti.gpx.ws.repository.GpxRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.listener.JobParameterExecutionContextCopyListener;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

/**
 * @author nhanpiti
 */

@Configuration
@EnableBatchProcessing
public class XMLParsersConfig {

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    @StepScope
    private GpxRepository gpxRepository;

    @Autowired
    public XMLParsersConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    @StepScope
    public StaxEventItemReader<Object> gpxItemReader(@Value("#{jobParameters['pathFile']}") String pathFile) {
        Jaxb2Marshaller unmarshaller = new Jaxb2Marshaller();
        unmarshaller.setClassesToBeBound(GpxEntity.class);
        StaxEventItemReader<Object> reader = new StaxEventItemReader<>();
        reader.setResource(new FileSystemResource(pathFile));
        reader.setFragmentRootElementName("gpx");
        reader.setUnmarshaller(unmarshaller);
        return reader;
    }

    @Bean
    @StepScope
    public ItemWriter<Object> gpxItemWriter() {
        return items -> {
            for (Object item : items) {
                if(item instanceof GpxEntity){
                    long currentTime = System.currentTimeMillis();
                    GpxEntity gpxEntity = (GpxEntity) item;
                    gpxEntity.setCreatedTime(currentTime);
                    gpxEntity.setUpdatedTime(currentTime);

                    if(gpxEntity.getGpxMetadata() != null) {
                        gpxEntity.getGpxMetadata().setGpx(gpxEntity);
                    }

                    if( gpxEntity.getWpts() != null) {
                        for (WptEntity e :
                                gpxEntity.getWpts()) {
                            e.setGpx(gpxEntity);
                            e.setCreatedTime(currentTime);
                        }
                    }

                    if(gpxEntity.getTrkpts() != null) {
                        for (TrkptEntity e :
                                gpxEntity.getTrkpts()) {
                            e.setGpx(gpxEntity);
                            e.setCreatedTime(currentTime);
                        }
                    }
                    gpxRepository.save(gpxEntity);
                }
            }
        };
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Object, Object>chunk(10)
                .reader(gpxItemReader(null))
                .writer(gpxItemWriter())
                .listener(new JobParameterExecutionContextCopyListener())
                .build();
    }


    @Bean
    public Job job() {
        return jobBuilderFactory.get("job")
                .start(step1())
                .build();
    }
}
