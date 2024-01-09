import { sveltekit } from '@sveltejs/kit/vite';
import { defineConfig } from 'vitest/config';

export default defineConfig({
	plugins: [sveltekit()],
	test: {
		include: ['src/**/*.{test,spec}.{js,ts}']
	},
	server: {
		proxy: {
			"/demo/": {
				target: "https://127.17.0.1:8090",
				rewrite: (path) => path.replace(/^\/demo/, ""),
			},
		},
	},
});
