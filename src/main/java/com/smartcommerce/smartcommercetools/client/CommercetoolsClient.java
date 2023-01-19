package com.smartcommerce.smartcommercetools.client;

// Required imports

import com.commercetools.api.client.ProjectApiRoot;
import com.commercetools.api.defaultconfig.ApiRootBuilder;
import com.commercetools.api.defaultconfig.ServiceRegion;
import io.vrap.rmf.base.client.oauth2.ClientCredentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class CommercetoolsClient {

	@Value("${ctp.clientId}")
	private String clientId;

	@Value("${ctp.clientSecret}")
	private String clientSecret;

	@Value("${ctp.projectKey}")
	private String projectKey;


	public ProjectApiRoot createApiClient() {
		final ProjectApiRoot apiRoot
		try {
			final ProjectApiRoot apiRoot = ApiRootBuilder.of()
				.defaultClient(ClientCredentials.of()
								.withClientId(clientId)
								.withClientSecret(clientSecret)
								.build(),
						ServiceRegion.GCP_EUROPE_WEST1)
				.build(projectKey);

		} finally {
			ap
		}

			return apiRoot;
	}

}