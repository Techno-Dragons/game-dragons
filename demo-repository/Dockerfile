# 1단계: 빌드 환경 설정
FROM node:18-alpine AS build

# 작업 디렉토리 설정
WORKDIR /app

# 의존성 파일 복사
COPY package.json package-lock.json ./

# 의존성 설치
RUN npm install --force --legacy-peer-deps

# 애플리케이션 소스 복사
COPY . .

# SvelteKit 애플리케이션 빌드
RUN npm run build

# 2단계: 실행 환경 설정
FROM node:18-alpine

# 작업 디렉토리 설정
WORKDIR /app

# 빌드 스테이지에서 생성된 파일 복사
COPY --from=build /app/build .
COPY --from=build /app/node_modules node_modules
COPY --from=build /app/package.json package.json

# 애플리케이션 포트 설정
EXPOSE 5173

# 환경변수 설정
ENV NODE_ENV=production
ENV PORT=5173

# 애플리케이션 실행
CMD ["node", "index.js"]