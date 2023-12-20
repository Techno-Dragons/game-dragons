<script>
	import {toastWarning} from "../../../app.js";
	import {page} from "$app/stores";
	import {onMount} from "svelte";

	let articleId = $page.param("id");
	let article = $state([]);

	async function articleDetail() {
		const response = await fetch(`/demo/article/${articleId}`);
		if (response.ok) {
			article = await response.json();

		} else {
			toastWarning();
		}
	}

	onMount(async () => {
		await articleDetail()
	});

</script>

<svelte:head>
	<title>Article</title>
	<meta name="description" content="Svelte demo app" />
</svelte:head>



<style>

</style>
