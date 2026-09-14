# AGENTS.md

Spring Boot 4 (parent 4.1.1) recipe web application on **Java 25** (enforced by the
maven-enforcer plugin). Single Maven module, package `guru.springframework.sfgrecipe`. Thymeleaf
UI backed by an H2 in-memory database.

## Build & test commands

- Full build: `./mvnw clean verify` — format checks (spring-javaformat + Spotless), unit tests
  (`*Test`, surefire) + IT (`*IT`, failsafe), Helm lint/template/package.
- Unit tests only: `./mvnw test`. Single test: `./mvnw test -Dtest=RecipeServiceImplTest`.
- `./mvnw clean install` additionally builds the Docker image and packages the Helm chart into
  `target/helm/repo/`. Skip the Docker build with `-Dskip.docker.build=true`.
- Start locally: `./mvnw spring-boot:run` (app on `:8080`, H2 console at `/h2-console`).

After changing code, always verify: run the relevant Maven goal above and report its output
(evidence, not just "done").

## Formatting is enforced (fails the `validate` phase)

- Java: Spring Java Format → fix with `./mvnw spring-javaformat:apply`.
- pom.xml, `**/*.md`, json, `src/main/resources/application*.yaml`, `**/*.sh`: Spotless → fix
  with `./mvnw spotless:apply`.
- `AGENTS.md` and `CLAUDE.md` are excluded from the markdown formatter.

## Sandbox build quirk

The kit sandbox mounts the repo via filesystem passthrough, which blocks symlinks — Spotless's
`npm install` (prettier) would fail with `EPERM` unless npm skips bin links. The sandbox kit sets
`npm_config_bin_links=false` globally (`spec.yaml` → `environment.variables`), so no manual export
is needed inside the kit sandbox. On a normal host (Windows/CI) this does not apply.

## Deploy / CI

- Deployment is Helm-only: chart in `helm-charts/`, packaged to `target/helm/repo/`, release name =
  artifactId `sfg-recipe`, namespace `sfg-recipe`, NodePort `30080`.
- CI (`.github/workflows/`): `maven-build.yml` builds + deploys snapshots and triggers
  `deploy-and-test-cluster.yml`; `release.yml` runs `mvn release:prepare release:perform` on
  main/master only (version must be `-SNAPSHOT`); SonarCloud analysis runs in the `analyze` job.
- Dependency updates are managed via `.github/dependabot.yml` and `.github/renovate.json`; validate
  changes with `renovate-config-validator`.
