package ats.managed_bean;

public enum RoleEnum {
	ADMIN {
		public String toString() {
			return "admin";
		}
	},

	USER{
		public String toString() {
			return "recruiter";
		}
	}
}
