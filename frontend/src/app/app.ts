import { Component, inject, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';

interface Organization {
  id: number;
  name: string;
  createdAt: string;
}

@Component({
  selector: 'app-root',
  templateUrl: './app.html',
  styleUrl: './app.scss',
})
export class App {
  private http = inject(HttpClient);
  private api = 'http://localhost:8080/api/organizations';

  organizations = signal<Organization[]>([]);
  error = signal('');

  constructor() {
    this.load();
  }

  load() {
    this.http.get<Organization[]>(this.api).subscribe({
      next: (data) => this.organizations.set(data),
      error: () => this.error.set('Cannot reach the backend. Is it running?'),
    });
  }

  add(name: string) {
    if (!name.trim()) return;
    this.http.post(this.api, { name }).subscribe(() => this.load());
  }
}