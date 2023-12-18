<script>
	import {toastWarning} from "../app.js";

	function test() {
		toastWarning("테스트");
	}

</script>

<svelte:head>
	<title>Home</title>
	<meta name="description" content="Svelte demo app" />
</svelte:head>

<section>
	<h1 class="text-6xl">홈</h1>

	<div class="card w-96 bg-base-100 shadow-xl">
		<figure><img src="https://daisyui.com/images/stock/photo-1606107557195-0e29a4b5b4aa.jpg" alt="Shoes" /></figure>
		<div class="card-body">
			<h2 class="card-title">Shoes!</h2>
			<p>If a dog chews shoes whose shoes does he choose?</p>
			<div class="card-actions justify-end">
				<button class="btn btn-primary" on:click={test}>Buy Now</button>
			</div>
		</div>
	</div>
</section>

<style>

</style>
