package cn.bjjoy.bms.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;


@SuppressWarnings("deprecation")
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	
	@Autowired
	private CommonInterceptor commonInterceptor;

	/**
	 * fastJson相关设置
	 */
	private FastJsonConfig getFastJsonConfig() {

		FastJsonConfig fastJsonConfig = new FastJsonConfig();
        // 在serializerFeatureList中添加转换规则
		List<SerializerFeature> serializerFeatureList = new ArrayList<SerializerFeature>();
		serializerFeatureList.add(SerializerFeature.PrettyFormat);
		serializerFeatureList.add(SerializerFeature.WriteMapNullValue);
		serializerFeatureList.add(SerializerFeature.WriteNullStringAsEmpty);
		serializerFeatureList.add(SerializerFeature.WriteNullListAsEmpty);
		serializerFeatureList.add(SerializerFeature.DisableCircularReferenceDetect);
		SerializerFeature[] serializerFeatures = serializerFeatureList.toArray(new SerializerFeature[serializerFeatureList.size()]);
		fastJsonConfig.setSerializerFeatures(serializerFeatures);

		return fastJsonConfig;
	}

	/**
	 * fastJson相关设置
	 */
	private FastJsonHttpMessageConverter4 fastJsonHttpMessageConverter() {

		FastJsonHttpMessageConverter4 fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter4();

		List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
		supportedMediaTypes.add(MediaType.parseMediaType("text/html;charset=UTF-8"));
		supportedMediaTypes.add(MediaType.parseMediaType("application/json"));

		fastJsonHttpMessageConverter.setSupportedMediaTypes(supportedMediaTypes);
		fastJsonHttpMessageConverter.setFastJsonConfig(getFastJsonConfig());

		return fastJsonHttpMessageConverter;
	}

	/**
	 * 添加fastJsonHttpMessageConverter到converters
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(fastJsonHttpMessageConverter());
	}

	/**
	 * 添加拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(commonInterceptor)
			.addPathPatterns("/**")
			.addPathPatterns("app/**");
		super.addInterceptors(registry);
	}

}
